package eu.vortexgg.kplugin

import eu.vortexgg.kplugin.db.DbManager
import eu.vortexgg.kplugin.listener.DbListener
import eu.vortexgg.kplugin.util.ConfigFile
import org.bukkit.plugin.java.JavaPlugin

class KPlugin : JavaPlugin() {
    lateinit var config: ConfigFile

    override fun onEnable() {
        instance = this

        config = ConfigFile("config.yml", this)

        DbManager.connect()

        registerListeners()
    }

    override fun onDisable() {
        DbManager.disconnect()
    }

    private fun registerListeners() {
        val pm = server.pluginManager
        pm.registerEvents(DbListener(), this)
    }

    companion object {
        @JvmStatic lateinit var instance: KPlugin
    }

}