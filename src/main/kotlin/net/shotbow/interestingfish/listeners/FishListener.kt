package net.shotbow.interestingfish.listeners

import net.shotbow.interestingfish.FishInfoFactory
import net.shotbow.interestingfish.FishInfoFactory.Companion.withColors
import net.shotbow.interestingfish.config.InterestingConfig
import net.shotbow.interestingfish.util.ItemUtility
import net.shotbow.interestingfish.util.ItemUtility.isFish
import net.shotbow.interestingfish.util.ItemUtility.meta
import org.bukkit.ChatColor
import org.bukkit.entity.Item
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Random

class FishListener(
    private val fishInfoFactory: FishInfoFactory,
    private val config: InterestingConfig
) : Listener {
    private val dateFormat: SimpleDateFormat = SimpleDateFormat(config.dateFormat)
    private val decimalFormat = DecimalFormat("0.00")
    private val random = Random()

    @EventHandler
    fun onFish(e: PlayerFishEvent) {
        val caught = e.caught
        if (caught is Item && caught.itemStack.isFish()) {
            if (
                !e.player.hasPermission("interestingfish.catch") ||
                config.requireLuckEnchant && !ItemUtility.fishingRodHasLuck(e.player.inventory) ||
                config.excludeLuckEnchant && ItemUtility.fishingRodHasLuck(e.player.inventory)
            ) {
                return
            }

            if (random.nextInt(100) < config.percentBreedsChance) {
                val playerName = if (config.caughtByNickname) e.player.displayName else e.player.name
                val (fishName, weight) = fishInfoFactory.makeNewFishInfo()
                val fish = caught.itemStack

                fish.meta {
                    setDisplayName("${ChatColor.RESET}$fishName")
                    lore = listOf(
                        (config.weightLabel + decimalFormat.format(weight) + config.weightUnit).withColors(),
                        (config.caughtByLabel + playerName).withColors(),
                        (config.dateLabel + dateFormat.format(Date())).withColors()
                    )
                }
            }
        }
    }
}
