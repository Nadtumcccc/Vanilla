package be.nadtum.vanilla.Player.Events

import be.nadtum.vanilla.Player.PlayerData
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

/**
 * class that handles player join and quit events.
 */
class Connection : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        val data = PlayerData(player)

        // Set the join message with player name
        event.joinMessage = "§7[§a+§7] ${player.name}"
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val player = event.player

        PlayerData.getPlayerData(player)?.save()

        // Set the quit message with player name
        event.quitMessage = "§7[§c-§7] ${player.name}"
    }
}