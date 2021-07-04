package net.shotbow.interestingfish.util

import org.bukkit.Material
import org.bukkit.Tag
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory
import org.bukkit.inventory.meta.ItemMeta

object ItemUtility {
    fun ItemStack.meta(updates: ItemMeta.() -> Unit) {
        itemMeta = itemMeta.also(updates)
    }

    fun ItemStack.isFish() = Tag.ITEMS_FISHES.isTagged(type)

    fun fishingRodHasLuck(inventory: PlayerInventory): Boolean =
        inventory.itemInEitherHand(Material.FISHING_ROD)
            ?.let { it.hasItemMeta() && it.itemMeta.hasEnchant(Enchantment.LUCK) } ?: false

    private fun PlayerInventory.itemInEitherHand(material: Material) =
        itemInMainHand.takeIf { it.type == material } ?: itemInOffHand.takeIf { it.type == material }
}
