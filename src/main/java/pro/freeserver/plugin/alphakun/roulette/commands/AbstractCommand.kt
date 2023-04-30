package pro.freeserver.plugin.alphakun.roulette.commands

import org.bukkit.command.CommandExecutor

abstract class AbstractCommand: CommandExecutor {
    abstract fun getLabel(): String
}