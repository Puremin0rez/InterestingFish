package net.shotbow.interestingfish.models

import java.io.Serializable
import kotlin.random.Random

typealias Breed = FishModifier
typealias Descriptor = FishModifier

data class FishModifier(
    val text: String,
    val minWeightModifier: Double,
    val maxWeightModifier: Double,
    val rollWeight: Int
) : Serializable {

    val weight: Double
        get() = minWeightModifier + Random.nextDouble() * maxWeightModifier - minWeightModifier

    companion object {
        private const val serialVersionUID: Long = 1
    }
}
