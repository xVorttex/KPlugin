package eu.vortexgg.kplugin.util

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.util.stream.Collectors

class ConfigFile(name: String, plugin: Plugin) : YamlConfiguration() {

    val file: File

    init {
        file = File(plugin.dataFolder, name)

        if (!file.exists()) {
            plugin.saveResource(name, false)
        }

        try {
            super.load(file)
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getInt(path: String) = super.getInt(path, 0)

    override fun getString(path: String) = BukkitUtil.color(super.getString(path, ""))

    override fun getBoolean(path: String) = super.getBoolean(path, false)

    override fun getDouble(path: String) = super.getDouble(path, 0.0)

    override fun getStringList(path: String): MutableList<String> = super.getStringList(path).stream().map { string -> BukkitUtil.color(string) }.collect(Collectors.toList())

}