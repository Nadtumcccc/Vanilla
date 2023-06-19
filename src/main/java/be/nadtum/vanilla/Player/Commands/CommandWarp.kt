package be.nadtum.vanilla.Player.Commands

import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File

fun getWarpLocation(warpName: String): Location? {
    val file: File = File("plugins" + File.separator + "Vanilla" + File.separator + "Warps.yml")
    val config: YamlConfiguration = YamlConfiguration.loadConfiguration(file)
    return config.getLocation("warps.$warpName")
}

class CommandWarp : CommandExecutor {

    private val file: File = File("plugins" + File.separator + "Vanilla" + File.separator + "Warps.yml")
    private val config: YamlConfiguration = YamlConfiguration.loadConfiguration(file)

    init {
        if (!file.exists()) {
            file.mkdirs()
            file.createNewFile()
        }
    }



    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) {
            sender.sendMessage("Vous ne pouvez utiliser cette commande que dans le jeu.")
            return false
        }

        if (args.isEmpty()) {
            // Aller au spawn
            val spawnLocation = getWarpLocation("spawn")
            if (spawnLocation != null) {
                sender.teleport(spawnLocation)
                sender.sendMessage("Vous avez été téléporté au spawn.")
            } else {
                sender.sendMessage("La position du spawn n'est pas définie.")
            }
            return true
        }

        if (args.size == 1) {
            // Aller au warp ciblé
            if (!sender.hasPermission("warp.teleport")) {
                sender.sendMessage("Vous n'avez pas la permission.")
                return false
            }
            val warpName = args[0]
            val warpLocation = getWarpLocation(warpName)
            if (warpLocation != null) {
                sender.teleport(warpLocation)
                sender.sendMessage("Vous avez été téléporté au warp '$warpName'.")
            } else {
                sender.sendMessage("Le warp '$warpName' n'existe pas.")
            }
            return true
        }

        if (args.size == 2) {
            if (!sender.hasPermission("warp.manage")) {
                sender.sendMessage("Vous n'avez pas la permission.")
                return false
            }
            val action = args[0]
            val warpName = args[1]

            when (action.toLowerCase()) {
                "add" -> {
                    // Ajouter un nouveau warp
                    val warpLocation = sender.location
                    config.set("warps.$warpName", warpLocation)
                    config.save(file)
                    sender.sendMessage("Le warp '$warpName' a été ajouté avec succès.")
                    return true
                }
                "remove" -> {
                    // Supprimer un warp existant
                    if (config.contains("warps.$warpName")) {
                        config.set("warps.$warpName", null)
                        config.save(file)
                        sender.sendMessage("Le warp '$warpName' a été supprimé avec succès.")
                    } else {
                        sender.sendMessage("Le warp '$warpName' n'existe pas.")
                    }
                    return true
                }
                else -> {
                    sender.sendMessage("Action invalide.")
                    return false
                }
            }
        }

        return false
    }
}
