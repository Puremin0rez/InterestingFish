package net.shotbow.interestingfish.listeners;

import net.shotbow.interestingfish.FishInfoFactory;
import net.shotbow.interestingfish.config.InterestingConfig;
import net.shotbow.interestingfish.objects.FishInfo;
import net.shotbow.interestingfish.utility.ItemUtility;
import org.bukkit.ChatColor;
import org.bukkit.Tag;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * User: Austin
 * Date: 5/18/13
 * Time: 1:34 AM
 * (c) lazertester
 */
public class FishListener implements Listener
{

    private final SimpleDateFormat dateFormat;
    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private FishInfoFactory fishInfoFactory;
    private InterestingConfig config;
    private Random random = new Random();

    public FishListener(FishInfoFactory fishInfoFactory, InterestingConfig config)
    {
        this.fishInfoFactory = fishInfoFactory;
        this.config = config;
        dateFormat = new SimpleDateFormat(config.dateFormat);
    }

    @EventHandler
    public void onFish(PlayerFishEvent e)
    {
        if (e.getCaught() instanceof Item && Tag.ITEMS_FISHES.isTagged(((Item) e.getCaught()).getItemStack().getType()))
        {
            if (config.requireLuckEnchant && !ItemUtility.fishingRodHasLuck(e.getPlayer().getInventory()))
                return;
            if (random.nextInt(100) < config.percentBreedsChance)
            {
                final String name = e.getPlayer().getName();
                final FishInfo fishInfo = fishInfoFactory.makeNewFishInfo();
                final ItemStack fish = ((Item) e.getCaught()).getItemStack();
                ItemUtility.renameItem(fish, fishInfo.getName());
                ItemUtility.setLore(fish, new ArrayList<String>()
                {{
                    add(replaceColorChars(config.weightLabel) + decimalFormat.format(fishInfo.getWeight()) + replaceColorChars(config.weightUnit));
                    add(replaceColorChars(config.caughtByLabel) + name);
                    add(replaceColorChars(config.dateLabel) + dateFormat.format(new Date()));
                }});
            }
        }
    }


    private String replaceColorChars(String text)
    {
        return text.replace('&', ChatColor.COLOR_CHAR);
    }
}
