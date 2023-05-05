package pro.freeserver.plugin.alphakun.roulette.commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pro.freeserver.plugin.alphakun.roulette.Roulette
import pro.freeserver.plugin.alphakun.roulette.Roulette.Companion.rouletteHandler
import pro.freeserver.plugin.alphakun.roulette.consts.PrizeData
import pro.freeserver.plugin.alphakun.roulette.handlers.SummonHandler
import kotlin.math.floor

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
                return true
            }
            // 引数summonの処理
            else if (args[0].equals("summon", ignoreCase = true) && sender is Player) {
                var title = "Roulette"
                var prizeDataLists = mutableListOf(PrizeData(Material.GRASS_BLOCK, 1.2), PrizeData(Material.STONE, 5.0))
                if (args.size >= 2 && args[1].isNotEmpty()) title = args[1]
                SummonHandler(title, prizeDataLists).summonRouletteSet(sender.location)
                return true
            }
            // 引数saveの処理
            else if (args[0].equals("save", ignoreCase = true) && sender is Player) {
                rouletteHandler.saveRoulette()
                sender.sendRichMessage("Data saved!")
                return true
            }
            else if (args[0].equals("list", ignoreCase = true) && sender is Player) {
                getRichList().forEach{
                    sender.sendRichMessage(it)
                }
                return true
            }
            else if (args[0].equals("remove", ignoreCase = true) && sender is Player) {
                if (args.size >= 2) {

                }
            }
        }
        return false
    }

    private fun getRichList(): MutableList<String> {
        val pluginName = Roulette.plugin.name
        val version = Roulette.plugin.pluginMeta.version

        val messageList = mutableListOf<String>()
        val rouletteList = rouletteHandler.getRouletteList()
        messageList.add(0, "")
        messageList.add(1, "--------[<blue>${pluginName}</blue>/<aqua>${version}</aqua>]--------")
        if (rouletteList.size <= 0) {
            messageList.add("<red>Roulette is not created :(</red>")
            messageList.add("<blue>/${getLabel()} summon [*name]</blue>: nameで指定したルーレットを生成")
        }
        rouletteList.forEachIndexed { index, rouletteData ->
            val loc = rouletteData.location
            messageList.add("${index + 1}: ${rouletteData.name} - x: ${Math.floor(loc.x)}, y: ${floor(loc.y)}, z: ${floor(loc.z)}")
        }
        messageList.add("")
        return messageList
    }

    private fun getRichHelp(): MutableList<String> {
        val pluginName = Roulette.plugin.name
        val version = Roulette.plugin.pluginMeta.version

        // TODO("あとでconfig保存に変更")
        return mutableListOf(
            "",
            "--------[<blue>${pluginName}</blue>/<aqua>${version}</aqua>]--------",
            "<blue>/${getLabel()}</blue>: このヘルプを表示",
            "<blue>/${getLabel()} summon [*name]</blue>: nameで指定したルーレットを生成",
            "<blue>/${getLabel()} save</blue>: ルーレットのデータをファイルに保存",
            "<blue>/${getLabel()} list</blue>: ルーレットの一覧を表示",
            ""
        )
    }
}