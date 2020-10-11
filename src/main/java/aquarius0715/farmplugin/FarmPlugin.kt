package aquarius0715.farmplugin

import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class FarmPlugin : JavaPlugin() {

    private val getSeeds = LoadConfig(this)

    val seeds = mutableMapOf<String, ItemStack>()
    val prefix = "[FarmPlugin]"

    override fun onEnable() {

        saveDefaultConfig()

        getSeeds.getSeeds()

        getCommand("farmadmin")!!.setExecutor(AdminCommands(this))

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

}