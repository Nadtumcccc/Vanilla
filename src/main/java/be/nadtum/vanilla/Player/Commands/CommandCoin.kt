package be.nadtum.vanilla.Player.Commands

import be.nadtum.vanilla.Player.PlayerData
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import java.util.ArrayList

class CommandCoin : CommandExecutor, TabExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) {
            Bukkit.getLogger().info("You can only use this command in-game.")
        }

        val player = sender as Player

        val data = PlayerData.getPlayerData(player)

        when (args.size){
            0 -> {
                player.sendMessage("vous avez ${data?.coin} coins.")
                return true
            }

            1 -> {
                return true
            }

            2 -> {
                if(!player.hasPermission("coin.manage")) {
                    player.sendMessage("vous n'avez pas la permission de faire cette action.")
                    return false
                }

                data?.coin =+ Integer.parseInt(args[1])

                player.sendMessage("vous avez ajouté ${args[1]} coins au joueur ${Bukkit.getPlayer(args[0])?.name}.")
                return true
            }
        }

        return false
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String>? {
        val list = ArrayList<String>()

        when (args.size) {
            0 -> {
                return list
            }

            1 -> {
                list.addAll(Bukkit.getOnlinePlayers().map(Player::getName))
                return list
            }

            2 -> {
                list.addAll(listOf("50", "10", "500", "1000"))
                return list
            }
        }

        return list
    }

}
