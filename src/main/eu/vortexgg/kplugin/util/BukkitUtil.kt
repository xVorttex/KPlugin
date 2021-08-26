package eu.vortexgg.kplugin.util

import org.bukkit.ChatColor

object BukkitUtil {

    @JvmStatic
    fun color(value: String): String = ChatColor.translateAlternateColorCodes('&', value)

}