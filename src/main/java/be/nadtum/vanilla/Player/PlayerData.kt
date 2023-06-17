package be.nadtum.vanilla.Player

import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeInstance
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File

private val playerDatas: HashMap<Player, PlayerData> = HashMap()

class PlayerData(private val player: Player) {

    private var coin: Int = 200
    private var health: Double = 20.0

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
            health = config.getDouble("profil.life")
        }
        return this
    }

    fun save() {
        val config: YamlConfiguration = YamlConfiguration.loadConfiguration(getFile())

        config.set("profil.coin", coin)
        config.set("profil.life", health)

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

    fun getHealth(): Double {
        return health
    }

    fun setHealth(health: Double) {
        this.health = health
    }

    fun setMaxHealth(player: Player, health: Double) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = health
    }

    fun addHealth(health: Double) {
        this.health += health
    }

    fun removeHealth(health: Double) {
        this.health -= health
    }

    fun getFile(): File {
        return file
    }
}
