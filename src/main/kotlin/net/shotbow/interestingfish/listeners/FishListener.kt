package net.shotbow.interestingfish.listeners

import net.shotbow.interestingfish.FishInfoFactory
import net.shotbow.interestingfish.config.InterestingConfig
import net.shotbow.interestingfish.util.ItemUtility
import net.shotbow.interestingfish.util.ItemUtility.isFish
import net.shotbow.interestingfish.util.ItemUtility.meta
import net.shotbow.interestingfish.util.TextUtility.withColors
import org.bukkit.ChatColor
import org.bukkit.entity.Item
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.random.Random

class FishListener(private val fishInfoFactory: FishInfoFactory, private val config: InterestingConfig) : Listener {
    private val dateFormat: SimpleDateFormat = SimpleDateFormat(config.dateFormat)
    private val decimalFormat = DecimalFormat("0.00")

    @EventHandler(ignoreCancelled = true)
    fun onFish(e: PlayerFishEvent) {
        // only new catches, not reeled in items
        if (e.state != PlayerFishEvent.State.CAUGHT_FISH) return
        val caught = e.caught
        if (caught is Item && caught.itemStack.isFish()) {
            if (
                !e.player.hasPermission("interestingfish.catch") ||
                (config.requireLuckEnchant && !ItemUtility.fishingRodHasLuck(e.player.inventory)) ||
                (config.excludeLuckEnchant && ItemUtility.fishingRodHasLuck(e.player.inventory))
            ) {
                return
            }

            if (Random.nextDouble() * 100 < config.percentBreedsChance) {
                val playerName = if (config.caughtByNickname) e.player.displayName else e.player.name
                val (fishName, weight) = fishInfoFactory.makeNewFishInfo()
                val fish = caught.itemStack

                fish.meta {
                    setDisplayName("${ChatColor.RESET}$fishName")
                    lore = listOf(
                        (config.weightLabel + decimalFormat.format(weight) + config.weightUnit).withColors(),
                        config.caughtByLabel.withColors(playerName),
                        (config.dateLabel + dateFormat.format(Date())).withColors()
                    )
                }
            }
        }
    }
}
