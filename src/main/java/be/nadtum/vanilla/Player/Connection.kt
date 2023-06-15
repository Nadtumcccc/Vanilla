package be.nadtum.vanilla.Player

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class Connection : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent){

        val player = event.player


        event.joinMessage = "§7[§+a§7] ${player.name}"
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent){

        val player = event.player


        event.quitMessage = "§7[§c-§7] ${player.name}"
    }

}
