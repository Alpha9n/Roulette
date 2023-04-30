package pro.freeserver.plugin.alphakun.roulette.commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pro.freeserver.plugin.alphakun.roulette.Roulette
import pro.freeserver.plugin.alphakun.roulette.consts.PrizeData
import pro.freeserver.plugin.alphakun.roulette.handlers.SummonHandler

class MainCommands: AbstractCommand() {

    override fun getLabel(): String {
        return "roulette"
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        // 設置コマンドのため、OPのみの実行
        if (!sender.isOp) return false

        val commandName = command.name
        if (commandName.equals(getLabel(), ignoreCase = true)) {

            // 引数なしの場合はヘルプ表示
            if (args?.isEmpty() != false) {
                for (message in getRichHelp()) {
                    sender.sendRichMessage(message)
                }
            }
            // 引数summonの処理
            else if (args[0].equals("summon", ignoreCase = true) && sender is Player) {
                var title = "Roulette"
                var prizeDataLists = mutableListOf(PrizeData(Material.GRASS_BLOCK, 1.2), PrizeData(Material.STONE, 5.0))
                if (args.size >= 2 && args[1].isNotEmpty()) title = args[1]
                SummonHandler(title, prizeDataLists).summonRouletteSet(sender.location)
            }
        }
        return false
    }

    private fun getRichHelp(): MutableList<String> {
        val pluginName = Roulette.plugin.name
        val version = Roulette.plugin.pluginMeta.version

        // TODO("あとでconfig保存に変更")
        return mutableListOf(
            "",
            "--------[<bold><blue>${pluginName}</blue>/<aqua>${version}</aqua></bold>]--------",
            "<bold><gold>/${getLabel()}</gold></bold>: このヘルプを表示",
            "<bold><gold>/${getLabel()} summon []</gold></bold>: ",
            ""
        )
    }
}