package net.shotbow.interestingfish.utility;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * User: Austin
 * Date: 2/22/13
 * Time: 9:44 PM
 * (c) lazertester
 */
public class ItemUtility {

    public static void renameItem(ItemStack item, String newName) {
        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RESET + newName);
        item.setItemMeta(itemMeta);
    }

    public static void setLore(ItemStack item, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public static boolean fishingRodHasLuck(PlayerInventory inventory) {
        ItemStack item = inventory.getItemInMainHand();
        if (item.getType() != Material.FISHING_ROD) {
            item = inventory.getItemInOffHand();
        }
        if (item.getType() == Material.FISHING_ROD) {
            if (item.hasItemMeta()) {
                return item.getItemMeta().hasEnchant(Enchantment.LUCK);
            }
        }
        return false;
    }
}
