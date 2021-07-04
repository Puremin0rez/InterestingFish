package net.shotbow.interestingfish

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.shotbow.interestingfish.InterestingFish.Companion.log
import net.shotbow.interestingfish.config.InterestingConfig
import net.shotbow.interestingfish.models.Breed
import net.shotbow.interestingfish.models.Descriptor
import net.shotbow.interestingfish.models.FishInfo
import org.bukkit.ChatColor
import kotlin.random.Random

class FishInfoFactory(private val config: InterestingConfig) {
    private val weightedDescriptors: MutableList<String> = mutableListOf()
    private val descriptorIndex: MutableMap<String, Descriptor> = mutableMapOf()
    private val weightedBreeds: MutableList<String> = mutableListOf()
    private val breedIndex: MutableMap<String, Breed> = mutableMapOf()

    init {
        for (descriptor in config.descriptors) {
            descriptorIndex[descriptor.text] = descriptor
            repeat(descriptor.rollWeight) { weightedDescriptors.add(descriptor.text) }
        }
        for (breed in config.breeds) {
            breedIndex[breed.text] = breed
            repeat(breed.rollWeight) { weightedDescriptors.add(breed.text) }
            for (i in 0..breed.rollWeight) weightedBreeds.add(breed.text)
        }
        log(descriptorIndex.size.toString() + " Descriptors & " + breedIndex.size + " Breeds loaded")
    }

    fun makeNewFishInfo(): FishInfo {
        val breed = breedIndex[weightedBreeds.random()]!!
        var weight = config.baseWeight + breed.weight
        var name = breed.text.withColors()

        if (Random.nextInt(100) < config.percentDescriptorChance) {
            val descriptor = descriptorIndex[weightedDescriptors.random()]!!

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
    }
}
