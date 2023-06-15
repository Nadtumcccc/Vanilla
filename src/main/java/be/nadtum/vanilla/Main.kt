package be.nadtum.vanilla

import be.nadtum.vanilla.Listeners.Commands
import be.nadtum.vanilla.Listeners.Events
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    override fun onEnable() {
        initializeListeners()
    }

    private fun initializeListeners() {
        Commands(this)
        Events(this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
