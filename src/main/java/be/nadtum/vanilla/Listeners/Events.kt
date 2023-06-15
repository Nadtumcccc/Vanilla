package be.nadtum.vanilla.Listeners

import be.nadtum.vanilla.Player.Connection
import be.nadtum.vanilla.Main
import be.nadtum.vanilla.Player.Death
import be.nadtum.vanilla.Player.Fight
import org.bukkit.plugin.PluginManager

class Events(private val main: Main) {

    private val pluginManager: PluginManager = main.server.pluginManager

    fun getEvents() {
        eventsPlayer()
    }

    private fun eventsPlayer() {
        val connectionListener = Connection() // Assuming Connection implements the appropriate listener interface
        pluginManager.registerEvents(Connection(), main)
        pluginManager.registerEvents(Death(), main)
        pluginManager.registerEvents(Fight(), main)
    }
}