package net.shotbow.interestingfish.listeners

import net.shotbow.interestingfish.config.InterestingConfig
import net.shotbow.interestingfish.util.ItemUtility.isFish
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class InteractListener(private val config: InterestingConfig) : Listener {
    @EventHandler
    fun onFish(e: PlayerInteractEntityEvent) {
        val rightClicked = e.rightClicked

        if (config.showItemFrameInfo && rightClicked is ItemFrame) {
            val item = rightClicked.item
            if (item.isFish() && !e.player.isSneaking) {
                e.isCancelled = true

                if (item.hasItemMeta()) {
                    val itemMeta = item.itemMeta!!
                    if (itemMeta.hasDisplayName()) {
                        e.player.sendMessage(itemMeta.displayName)
                    }
                    if (itemMeta.hasLore()) {
                        for (lore in itemMeta.lore!!) {
                            e.player.sendMessage(lore)
                        }
                    }
                }
            }
        }
    }
}
