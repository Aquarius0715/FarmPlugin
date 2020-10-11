package aquarius0715.farmplugin

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class LoadConfig(private val plugin: FarmPlugin) {

    fun getSeeds() {

        val config = plugin.config

        plugin.reloadConfig()

        for (seedName in plugin.config.getConfigurationSection("Seeds")!!.getKeys(false)) {

            val itemStack = ItemStack(Material.matchMaterial("Seeds.$seedName.Material")!!)
            val itemMeta = itemStack.itemMeta

            itemMeta.setDisplayName(config.getString("Seeds.$seedName.DisplayName"))
            itemMeta.lore = config.getStringList("Seeds.$seedName.Lore")
            itemMeta.persistentDataContainer.set(NamespacedKey(plugin, "MaxGrowUpTime"), PersistentDataType.INTEGER, config.getInt("Seeds.$seedName.MaxGrowUpTime"))
            itemMeta.persistentDataContainer.set(NamespacedKey(plugin, "WitherTime"), PersistentDataType.INTEGER, config.getInt("Seeds.$seedName.WitherTime"))
            itemMeta.persistentDataContainer.set(NamespacedKey(plugin, "GrowUpStats"), PersistentDataType.BYTE, 1)
            itemMeta.persistentDataContainer.set(NamespacedKey(plugin, "WaterEffect"), PersistentDataType.INTEGER, config.getInt("Seeds.$seedName.WaterEffect"))

            itemStack.itemMeta = itemMeta

            plugin.seeds[seedName] = itemStack

        }

    }

}