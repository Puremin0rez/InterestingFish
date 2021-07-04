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
            if (item != null && item.isFish() && !e.player.isSneaking) {
                e.isCancelled = true

                if (item.hasItemMeta()) {
                    if (item.itemMeta.hasDisplayName()) {
                        e.player.sendMessage(item.itemMeta.displayName)
                    }
                    if (item.itemMeta.hasLore()) {
                        for (lore in item.itemMeta.lore) {
                            e.player.sendMessage(lore)
                        }
                    }
                }
            }
        }
    }
}
