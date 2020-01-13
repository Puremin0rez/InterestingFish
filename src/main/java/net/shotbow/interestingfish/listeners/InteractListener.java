package net.shotbow.interestingfish.listeners;

import net.shotbow.interestingfish.FishInfoFactory;
import net.shotbow.interestingfish.config.InterestingConfig;
import net.shotbow.interestingfish.objects.FishInfo;
import net.shotbow.interestingfish.utility.ItemUtility;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * User: Austin
 * Date: 5/18/13
 * Time: 1:47 AM
 * (c) lazertester
 */
public class InteractListener implements Listener {


    @EventHandler
    public void onFish(PlayerInteractEntityEvent e)
    {
        if(e.getRightClicked() instanceof ItemFrame)
        {
            final ItemStack item = ((ItemFrame) e.getRightClicked()).getItem();
            if(item != null && item.getType() == Material.RAW_FISH && !e.getPlayer().isSneaking())
            {
                e.setCancelled(true);
                final Player player = e.getPlayer();
                if(item.hasItemMeta())
                {
                    if(item.getItemMeta().hasDisplayName()) {
                        player.sendMessage(item.getItemMeta().getDisplayName());
                    }
                    if(item.getItemMeta().hasLore())
                    {
                        for (String lore : item.getItemMeta().getLore()) {
                            player.sendMessage(lore);
                        }
                    }
                }
            }
        }
    }
}
