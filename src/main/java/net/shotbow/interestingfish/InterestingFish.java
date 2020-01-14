package net.shotbow.interestingfish;

import net.shotbow.interestingfish.config.InterestingConfig;
import net.shotbow.interestingfish.listeners.FishListener;
import net.shotbow.interestingfish.listeners.InteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * User: Austin
 * Date: 5/18/13
 * Time: 12:43 AM
 * (c) lazertester
 */
public class InterestingFish extends JavaPlugin
{


    private InterestingConfig config;

    public static void log(String message)
    {
        Bukkit.getLogger().info("[InterestingFish] " + message);
    }

    public void onEnable()
    {
        config = new InterestingConfig(getDataFolder().getPath());
        config.initialize();
        final FishInfoFactory fishInfoFactory = new FishInfoFactory(config);
        Bukkit.getPluginManager().registerEvents(new FishListener(fishInfoFactory, config), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        log("InterestingFish is enabled!");
    }
}
