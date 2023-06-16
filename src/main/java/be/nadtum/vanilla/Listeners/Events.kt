package be.nadtum.vanilla.Listeners

import be.nadtum.vanilla.Player.Events.Connection
import be.nadtum.vanilla.Main
import be.nadtum.vanilla.Player.Events.Death
import be.nadtum.vanilla.Player.Events.Fight
import org.bukkit.plugin.PluginManager

class Events(private val main: Main) {

    private val pm: PluginManager = main.server.pluginManager

    init {
        eventsPlayer()
    }

    private fun eventsPlayer() {
        pm.registerEvents(Connection(), main)
        pm.registerEvents(Death(), main)
        pm.registerEvents(Fight(), main)
    }
}