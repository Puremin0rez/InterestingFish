package net.shotbow.interestingfish.config

import net.shotbow.interestingfish.InterestingFish.Companion.log
import net.shotbow.interestingfish.models.Breed
import net.shotbow.interestingfish.models.Descriptor

class InterestingConfig(path: String?) : ConfigObject(path, "config.yml") {
    @JvmField
    var baseWeight = .5
    @JvmField
    var minWeight = .01
    @JvmField
    var percentDescriptorChance = 75.0
    @JvmField
    var percentBreedsChance = 100.0
    @JvmField
    var requireLuckEnchant = false
    @JvmField
    var excludeLuckEnchant = false
    @JvmField
    var showItemFrameInfo = true
    @JvmField
    var weightLabel = "<aqua>Weight: <white>"
    @JvmField
    var weightUnit = "lbs"
    @JvmField
    var caughtByLabel = "<gold>Caught by: <white>"
    @JvmField
    var caughtByNickname = false
    @JvmField
    var dateLabel = "<gold>"
    @JvmField
    var dateFormat = "M/d/yy h:mm aa"
    @JvmField
    var descriptorList: List<Map<String, Any>> = listOf(
        mapOf(
            "minWeightModifier" to 5.0,
            "maxWeightModifier" to 20.0,
            "rollWeight" to 1,
            "text" to "<green><bold>Big",
        ),
        mapOf(
            "minWeightModifier" to -20.0,
            "maxWeightModifier" to 0.0,
            "rollWeight" to 1,
            "text" to "<green><italic>Tiny",
        ),
        mapOf(
            "minWeightModifier" to 1.0,
            "maxWeightModifier" to 100.0,
            "rollWeight" to 1,
            "text" to "<color:#4B0082>Kyori",
        ),
        mapOf(
            "minWeightModifier" to 1.0,
            "maxWeightModifier" to 100.0,
            "rollWeight" to 1,
            "text" to "<rainbow>Rainbow",
        ),
        mapOf(
            "minWeightModifier" to 1.0,
            "maxWeightModifier" to 100.0,
            "rollWeight" to 1,
            "text" to "<gradient:light_purple:blue><underlined>Adventurous",
        ),
    )
    @JvmField
    var breedsList: List<Map<String, Any>> = listOf(
        mapOf(
            "minWeightModifier" to 1.0,
            "maxWeightModifier" to 20.0,
            "rollWeight" to 1,
            "text" to "<yellow>Trout"
        ),
        mapOf(
            "minWeightModifier" to 20.0,
            "maxWeightModifier" to 50.0,
            "rollWeight" to 1,
            "text" to "<aqua>Tuna"
        )
    )

    override fun initialize() {
        super.initialize()
        log("Initialized with " + descriptorList.size + " descriptors")
    }

    val descriptors: Iterable<Descriptor>
        get() = descriptorList.map {
            Descriptor(
                text = it["text"] as String,
                minWeightModifier = it["minWeightModifier"] as Double,
                maxWeightModifier = it["maxWeightModifier"] as Double,
                rollWeight = it["rollWeight"] as Int
            )
        }

    val breeds: Iterable<Breed>
        get() = breedsList.map {
            Breed(
                text = it["text"] as String,
                minWeightModifier = it["minWeightModifier"] as Double,
                maxWeightModifier = it["maxWeightModifier"] as Double,
                rollWeight = it["rollWeight"] as Int
            )
        }
}
