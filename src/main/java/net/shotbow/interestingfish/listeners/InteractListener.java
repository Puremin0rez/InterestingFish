package net.shotbow.interestingfish.listeners;

import net.shotbow.interestingfish.config.InterestingConfig;
import org.bukkit.Tag;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * User: Austin
 * Date: 5/18/13
 * Time: 1:47 AM
 * (c) lazertester
 */
public class InteractListener implements Listener {

    private final InterestingConfig config;

    public InteractListener(InterestingConfig config) {
        this.config = config;
    }

    @EventHandler
    public void onFish(PlayerInteractEntityEvent e) {
        if (config.showItemFrameInfo && e.getRightClicked() instanceof ItemFrame) {
            final ItemStack item = ((ItemFrame) e.getRightClicked()).getItem();
            if (item != null && Tag.ITEMS_FISHES.isTagged(item.getType()) && !e.getPlayer().isSneaking()) {
                e.setCancelled(true);
                final Player player = e.getPlayer();
                if (item.hasItemMeta()) {
                    if (item.getItemMeta().hasDisplayName()) {
                        player.sendMessage(item.getItemMeta().getDisplayName());
                    }
                    if (item.getItemMeta().hasLore()) {
                        for (String lore : item.getItemMeta().getLore()) {
                            player.sendMessage(lore);
                        }
                    }
                }
            }
        }
    }
}
