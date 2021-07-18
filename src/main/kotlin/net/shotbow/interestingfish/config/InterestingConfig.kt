package net.shotbow.interestingfish.config

import net.shotbow.interestingfish.models.Breed
import net.shotbow.interestingfish.models.Descriptor

data class InterestingConfig(
    val baseWeight: Double = .5,
    val minWeight: Double = .01,
    val percentDescriptorChance: Double = 75.0,
    val percentBreedsChance: Double = 100.0,
    val requireLuckEnchant: Boolean = false,
    val excludeLuckEnchant: Boolean = false,
    val showItemFrameInfo: Boolean = true,
    val weightLabel: String = "<aqua>Weight: <white>",
    val weightUnit: String = "lbs",
    val caughtByLabel: String = "<gold>Caught by: <white>",
    val caughtByNickname: Boolean = false,
    val dateLabel: String = "<gold>",
    val dateFormat: String = "M/d/yy h:mm aa",
    val descriptorList: List<Descriptor> = listOf(
        Descriptor(
            text = "<green><bold>Big",
            minWeightModifier = 5.0,
            maxWeightModifier = 20.0,
            rollWeight = 1
        ),
        Descriptor(
            text = "<green><italic>Tiny",
            minWeightModifier = -20.0,
            maxWeightModifier = 0.0,
            rollWeight = 1
        ),
        Descriptor(
            text = "<color:#4B0082>Kyori",
            minWeightModifier = 1.0,
            maxWeightModifier = 100.0,
            rollWeight = 1
        ),
        Descriptor(
            text = "<rainbow>Rainbow",
            minWeightModifier = 1.0,
            maxWeightModifier = 100.0,
            rollWeight = 1
        ),
        Descriptor(
            text = "<gradient:light_purple:blue><underlined>Adventurous",
            minWeightModifier = 1.0,
            maxWeightModifier = 100.0,
            rollWeight = 1
        ),
    ),
    val breedsList: List<Breed> = listOf(
        Breed(
            text = "<yellow>Trout",
            minWeightModifier = 1.0,
            maxWeightModifier = 20.0,
            rollWeight = 1
        ),
        Breed(
            text = "<aqua>Tuna",
            minWeightModifier = 20.0,
            maxWeightModifier = 50.0,
            rollWeight = 1
        )
    )
)
