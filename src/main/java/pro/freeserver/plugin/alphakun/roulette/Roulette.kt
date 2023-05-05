package pro.freeserver.plugin.alphakun.roulette

import org.bukkit.NamespacedKey
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import pro.freeserver.plugin.alphakun.roulette.commands.AbstractCommand
import pro.freeserver.plugin.alphakun.roulette.commands.MainCommands
import pro.freeserver.plugin.alphakun.roulette.handlers.RouletteHandler

class Roulette : JavaPlugin() {
    override fun onEnable() {
        varInit()
        loadCommand()
        loadConfig()
    }

    override fun onDisable() {
    }

    private fun varInit() {
        plugin = this
        rouletteTag = NamespacedKey(plugin,"RouletteTag")
        rouletteHandler = RouletteHandler()

    }

    private fun loadCommand() {
        for (command in commands) {
            getCommand(command.getLabel())?.setExecutor(command)
        }
    }

    private fun loadConfig() {
        saveDefaultConfig()
    }

    companion object {
        lateinit var plugin: Plugin
        lateinit var rouletteTag: NamespacedKey
        lateinit var rouletteHandler: RouletteHandler
        var commands = mutableListOf<AbstractCommand>(
            MainCommands()
        )
    }
}
