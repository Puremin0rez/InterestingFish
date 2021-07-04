package net.shotbow.interestingfish

import net.shotbow.interestingfish.config.InterestingConfig
import net.shotbow.interestingfish.listeners.FishListener
import net.shotbow.interestingfish.listeners.InteractListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class InterestingFish : JavaPlugin() {
    override fun onEnable() {
        val config = InterestingConfig(dataFolder.path)
        config.initialize()
        val fishInfoFactory = FishInfoFactory(config)
        Bukkit.getPluginManager().registerEvents(FishListener(fishInfoFactory, config), this)
        Bukkit.getPluginManager().registerEvents(InteractListener(config), this)
        log("InterestingFish is enabled!")
    }

    companion object {
        @JvmStatic
        fun log(message: String) {
            Bukkit.getLogger().info("[InterestingFish] $message")
        }
    }
}
