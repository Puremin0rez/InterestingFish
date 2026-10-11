package net.shotbow.interestingfish

import net.shotbow.interestingfish.config.InterestingConfig
import net.shotbow.interestingfish.models.FishInfo
import net.shotbow.interestingfish.models.FishModifier
import net.shotbow.interestingfish.util.TextUtility.withColors
import org.bukkit.ChatColor
import kotlin.random.Random

class FishInfoFactory(private val config: InterestingConfig) {
    // colored names of every descriptor and breed
    private val names = (config.descriptorList + config.breedsList).associateWith { it.text.withColors() }

    init {
        InterestingFish.log("${config.descriptorList.size} Descriptors & ${config.breedsList.size} Breeds loaded")
    }

    fun makeNewFishInfo(): FishInfo {
        val breed = config.breedsList.pickWeighted()!!
        var weight = config.baseWeight + breed.weight
        var name = names.getValue(breed)

        if (Random.nextDouble() * 100 < config.percentDescriptorChance) {
            // null when every descriptor has a rollWeight of 0
            config.descriptorList.pickWeighted()?.let { descriptor ->
                name = "${names.getValue(descriptor)}${ChatColor.RESET} $name"
                weight += descriptor.weight
            }
        }

        return FishInfo(name, weight.coerceAtLeast(config.minWeight))
    }

    companion object {
        // odds are proportional to rollWeight, null if nothing can roll
        fun List<FishModifier>.pickWeighted(): FishModifier? {
            val total = sumOf { it.rollWeight.toLong() }
            if (total == 0L) return null
            var roll = Random.nextLong(total)
            return first {
                roll -= it.rollWeight
                roll < 0
            }
        }
    }
}
