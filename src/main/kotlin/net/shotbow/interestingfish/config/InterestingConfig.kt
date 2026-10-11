package net.shotbow.interestingfish.config

import net.shotbow.interestingfish.models.Breed
import net.shotbow.interestingfish.models.Descriptor
import net.shotbow.interestingfish.models.FishModifier
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat

data class InterestingConfig(
    val baseWeight: Double,
    val minWeight: Double,
    val percentDescriptorChance: Double,
    val percentBreedsChance: Double,
    val requireLuckEnchant: Boolean,
    val excludeLuckEnchant: Boolean,
    val showItemFrameInfo: Boolean,
    val weightLabel: String,
    val weightUnit: String,
    val caughtByLabel: String,
    val caughtByNickname: Boolean,
    val dateLabel: String,
    val dateFormat: String,
    val descriptorList: List<Descriptor>,
    val breedsList: List<Breed>,
) {
    companion object {
        // returns null when the config can't be used
        fun load(plugin: JavaPlugin): InterestingConfig? {
            plugin.saveDefaultConfig() // make sure we have at least a default config file
            val file = File(plugin.dataFolder, "config.yml")

            // stop if the config can't be parsed
            try {
                YamlConfiguration().load(file)
            } catch (e: Exception) {
                plugin.logger.severe("config.yml could not be read, fix it and restart: ${e.message}")
                return null
            }

            // append missing options, except blank ones
            val defaults = plugin.config.defaults!!
            val contents = file.readText()
            val missing = (defaults.getKeys(false) - plugin.config.getKeys(false))
                .filterNot { Regex("^${Regex.escape(it)}:", RegexOption.MULTILINE).containsMatchIn(contents) }
            if (missing.isNotEmpty()) {
                plugin.logger.info("Adding missing config options: ${missing.joinToString()}")
                val additions = YamlConfiguration()
                missing.forEach { additions.set(it, defaults.get(it)) }
                val separator = if (contents.isEmpty() || contents.endsWith("\n")) "" else "\n"
                try {
                    file.appendText(separator + additions.saveToString())
                    plugin.reloadConfig()
                } catch (e: IOException) {
                    plugin.logger.warning("Couldn't add them to config.yml, using their defaults: ${e.message}")
                }
            }

            return try {
                from(plugin.config) { plugin.logger.warning("config.yml: $it") }
            } catch (e: IllegalArgumentException) {
                plugin.logger.severe("config.yml: ${e.message}, fix it and restart")
                null
            }
        }

        internal fun from(config: ConfigurationSection, warn: (String) -> Unit): InterestingConfig {
            val defaults = config.defaultSection!!

            fun double(key: String): Double {
                val value = config.get(key)
                if (value is Number && value.toDouble().isFinite()) return value.toDouble()
                warn("$key must be a number but is '$value', using the default")
                return defaults.getDouble(key)
            }

            fun boolean(key: String): Boolean = when (val value = config.get(key)) {
                is Boolean -> value
                else -> {
                    warn("$key must be true or false but is '$value', using the default")
                    defaults.getBoolean(key)
                }
            }

            fun string(key: String): String = when (val value = config.get(key)) {
                is String, is Number, is Boolean -> value.toString()
                else -> {
                    warn("$key must be text but is '$value', using the default")
                    defaults.getString(key)!!
                }
            }

            fun modifiers(key: String): List<FishModifier> {
                val entries = config.get(key) as? List<*> ?: run {
                    warn("$key must be a list, using the default")
                    defaults.getList(key)!!
                }
                return entries.mapIndexedNotNull { i, entry ->
                    parseModifier(entry)
                        .onFailure { warn("$key entry ${i + 1} skipped: ${it.message}") }
                        .onSuccess {
                            if (it.rollWeight == 0) warn("$key entry '${it.text}' has rollWeight 0 and never rolls")
                        }
                        .getOrNull()
                }
            }

            var dateFormat = string("dateFormat")
            try {
                SimpleDateFormat(dateFormat)
            } catch (e: IllegalArgumentException) {
                warn("dateFormat '$dateFormat' is invalid (${e.message}), using the default")
                dateFormat = defaults.getString("dateFormat")!!
            }

            val breeds = modifiers("breedsList")
            require(breeds.any { it.rollWeight > 0 }) {
                "breedsList needs at least one entry with a rollWeight above 0"
            }

            return InterestingConfig(
                baseWeight = double("baseWeight"),
                minWeight = double("minWeight"),
                percentDescriptorChance = double("percentDescriptorChance"),
                percentBreedsChance = double("percentBreedsChance"),
                requireLuckEnchant = boolean("requireLuckEnchant"),
                excludeLuckEnchant = boolean("excludeLuckEnchant"),
                showItemFrameInfo = boolean("showItemFrameInfo"),
                weightLabel = string("weightLabel"),
                weightUnit = string("weightUnit"),
                caughtByLabel = string("caughtByLabel"),
                caughtByNickname = boolean("caughtByNickname"),
                dateLabel = string("dateLabel"),
                dateFormat = dateFormat,
                descriptorList = modifiers("descriptorList"),
                breedsList = breeds
            )
        }

        private fun parseModifier(entry: Any?): Result<FishModifier> = runCatching {
            require(entry is Map<*, *>) { "must have text, minWeightModifier, maxWeightModifier and rollWeight" }

            fun number(key: String): Double {
                val value = (entry[key] as? Number)?.toDouble()
                require(value != null && value.isFinite()) { "$key must be a number" }
                return value
            }

            val text = entry["text"]
            require(text is String || text is Number) { "text is missing" }
            val rollWeight = number("rollWeight")
            require(rollWeight % 1 == 0.0 && rollWeight in 0.0..Int.MAX_VALUE.toDouble()) {
                "rollWeight must be a whole number, 0 or more"
            }

            FishModifier(
                text = text.toString(),
                minWeightModifier = number("minWeightModifier"),
                maxWeightModifier = number("maxWeightModifier"),
                rollWeight = rollWeight.toInt()
            )
        }
    }
}
