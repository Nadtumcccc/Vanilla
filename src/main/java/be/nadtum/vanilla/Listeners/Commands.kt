package be.nadtum.vanilla.Listeners

import be.nadtum.vanilla.Main
import be.nadtum.vanilla.Player.Commands.CommandCoin

class Commands(main: Main) {

    init {
        main.getCommand("money")?.setExecutor(CommandCoin())
    }

}
