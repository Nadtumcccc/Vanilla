package be.nadtum.vanilla.Player

import org.bukkit.entity.Player
import java.io.File

private val playerDatas :HashMap<Player, PlayerData> = HashMap()

class PlayerData(

    val name :String,
    val coin :Int,
    val life :Double,

) {

    private val file = File("plugins/Vanilla/Players/$name.yml")

    init {

        //check if file player exist


    }

    companion object {

    }

    fun getPlayerDatas(player: Player): PlayerData? {
        return playerDatas[player]
    }

    fun getFile(): File{
        return file
    }

}