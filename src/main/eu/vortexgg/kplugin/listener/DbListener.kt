package eu.vortexgg.kplugin.listener

import eu.vortexgg.kplugin.KPlugin
import eu.vortexgg.kplugin.user.KPlayer
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class DbListener : Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val p = e.player;
        val vp = KPlayer(p.uniqueId, p.name)
        Bukkit.getScheduler().runTaskAsynchronously(KPlugin.instance) {
            vp.load()
            vp.register()
        }
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        val vp = KPlayer.players[e.player.uniqueId]
        if (vp != null) {
            Bukkit.getScheduler().runTaskAsynchronously(KPlugin.instance) {
                vp.save()
                vp.unregister()
            }
        }
    }

}