package pro.freeserver.plugin.alphakun.roulette

import org.bukkit.NamespacedKey
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import pro.freeserver.plugin.alphakun.roulette.commands.AbstractCommand
import pro.freeserver.plugin.alphakun.roulette.commands.MainCommands
import pro.freeserver.plugin.alphakun.roulette.consts.RouletteData
import pro.freeserver.plugin.alphakun.roulette.utils.GsonUtil

class Roulette : JavaPlugin() {
    override fun onEnable() {
        varInit()
        loadCommand()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun varInit() {
        plugin = this
        rouletteTag = NamespacedKey(plugin,"RouletteTag")
        // TODO("Jsonファイルから読み込むよう設定する")
        rouletteDataList = GsonUtil<MutableList<RouletteData>>("rouletteDataList.json", plugin).fromJson()
    }

    private fun loadCommand() {
        for (command in commands) {
            getCommand(command.getLabel())?.setExecutor(command)
        }
    }

    companion object {
        lateinit var plugin: Plugin
        lateinit var rouletteTag: NamespacedKey
        var rouletteDataList: MutableList<RouletteData>? = null
        var commands = mutableListOf<AbstractCommand>(
            MainCommands()
        )
    }
}
