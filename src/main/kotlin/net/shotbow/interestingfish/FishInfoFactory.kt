package net.shotbow.interestingfish

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.shotbow.interestingfish.config.InterestingConfig
import net.shotbow.interestingfish.models.Breed
import net.shotbow.interestingfish.models.Descriptor
import net.shotbow.interestingfish.models.FishInfo
import net.shotbow.interestingfish.models.FishModifier
import org.bukkit.ChatColor
import kotlin.random.Random

class FishInfoFactory(private val config: InterestingConfig) {
    private val weightedDescriptors: Array<Descriptor> = config.descriptorList.toWeightedArray()
    private val weightedBreeds: Array<Breed> = config.breedsList.toWeightedArray()

    fun makeNewFishInfo(): FishInfo {
        val breed = weightedBreeds.random()
        var weight = config.baseWeight + breed.weight
        var name = breed.text.withColors()

        if (Random.nextInt(100) < config.percentDescriptorChance) {
            val descriptor = weightedDescriptors.random()

            name = "${descriptor.text.withColors()}${ChatColor.RESET} $name"
            weight += descriptor.weight
        }

        return FishInfo(name, weight.coerceAtLeast(config.minWeight))
    }

    companion object {
        private val legacyHexSerializer =
            LegacyComponentSerializer.builder().hexColors().useUnusualXRepeatedCharacterHexFormat().build()

        fun String.withColors() =
            legacyHexSerializer.serialize(MiniMessage.get().parse(replace('&', ChatColor.COLOR_CHAR)))

        fun Iterable<FishModifier>.toWeightedArray() =
            flatMap { modifier -> Array(modifier.rollWeight) { modifier }.toList() }.toTypedArray()
    }
}
