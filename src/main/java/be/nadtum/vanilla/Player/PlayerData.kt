package be.nadtum.vanilla.Player

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File

private val playerDatas: HashMap<Player, PlayerData> = HashMap()

class PlayerData(private val player: Player) {

    private var coin: Int = 200
    private var life: Double = 20.0

    private val file: File

    init {
        val dataFolder = File("plugins/Vanilla/Players")
        dataFolder.mkdirs()

        file = File(dataFolder, "${player.name}.yml")
        file.createNewFile()

        load()

        setPlayerData(player, this)
    }

    private fun load(): PlayerData {
        val config: YamlConfiguration = YamlConfiguration.loadConfiguration(getFile())

        if(config.contains("profil")){
            coin = config.getInt("profil.coin")
            life = config.getDouble("profil.life")
        }
        return this
    }

    fun save() {
        val config: YamlConfiguration = YamlConfiguration.loadConfiguration(getFile())

        config.set("profil.coin", coin)
        config.set("profil.life", life)

        config.save(file)
    }

    companion object {

        fun getPlayerData(player: Player): PlayerData? {
            if(!playerDatas.containsKey(player)) {
                return null
            }
            return playerDatas[player]
        }

        fun setPlayerData(player: Player, data: PlayerData) {
            playerDatas[player] = data
        }

        fun removePlayerData(player: Player) {
            if(playerDatas.containsKey(player)){
                playerDatas.remove(player)
            }
        }

    }

    fun getCoin(): Int {
        return coin
    }

    fun setCoin(coin: Int) {
        this.coin = coin
    }

    fun addCoin(coin: Int) {
        this.coin += coin
    }

    fun removeCoin(coin: Int){
        this.coin -= coin
    }

    fun getLife(): Double {
        return life
    }

    fun setLife(life: Double) {
        this.life = life
    }

    fun addLife(life: Double) {
        this.life += life
    }

    fun removeLife(life: Double) {
        this.life -= life
    }

    fun getFile(): File {
        return file
    }
}
