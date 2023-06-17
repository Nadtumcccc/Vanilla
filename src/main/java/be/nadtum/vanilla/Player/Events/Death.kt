package be.nadtum.vanilla.Player.Events

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class Death : Listener {

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {

        val player = event.entity
        val message = event.deathMessage

    }

}
