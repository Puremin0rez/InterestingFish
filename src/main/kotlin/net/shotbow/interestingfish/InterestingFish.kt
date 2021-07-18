package net.shotbow.interestingfish

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.source.yaml
import com.uchuhimo.konf.toValue
import net.shotbow.interestingfish.config.InterestingConfig
import net.shotbow.interestingfish.listeners.FishListener
import net.shotbow.interestingfish.listeners.InteractListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class InterestingFish : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig() // make sure we have at least a default config file

        val config = Config().from.yaml.file(File(dataFolder, "config.yml")).toValue<InterestingConfig>()
        log(config.toString())
        val fishInfoFactory = FishInfoFactory(config)

        Bukkit.getPluginManager().registerEvents(FishListener(fishInfoFactory, config), this)
        Bukkit.getPluginManager().registerEvents(InteractListener(config), this)
        log("InterestingFish is enabled!")
    }

    companion object {
        fun log(message: String) {
            Bukkit.getLogger().info("[InterestingFish] $message")
        }
    }
}
