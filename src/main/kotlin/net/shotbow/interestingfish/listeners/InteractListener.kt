package net.shotbow.interestingfish.listeners

import net.shotbow.interestingfish.config.InterestingConfig
import net.shotbow.interestingfish.util.ItemUtility.isFish
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.EquipmentSlot

class InteractListener(private val config: InterestingConfig) : Listener {
    @EventHandler
    fun onInteract(e: PlayerInteractEntityEvent) {
        val rightClicked = e.rightClicked
        if (!config.showItemFrameInfo || rightClicked !is ItemFrame || e.player.isSneaking) return

        val item = rightClicked.item
        val itemMeta = if (item.isFish() && item.hasItemMeta()) item.itemMeta!! else return
        // plain fish are left alone
        if (!itemMeta.hasDisplayName() && !itemMeta.hasLore()) return

        // cancel both hands, only the main hand shows the info
        e.isCancelled = true
        if (e.hand != EquipmentSlot.HAND) return

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
