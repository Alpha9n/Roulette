package pro.freeserver.plugin.alphakun.roulette.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.bukkit.plugin.Plugin
import pro.freeserver.plugin.alphakun.roulette.Roulette
import pro.freeserver.plugin.alphakun.roulette.handlers.RouletteHandler
import java.io.*

class GsonUtil(fileName: String, plugin: Plugin) {
    private val EXTENSION = ".json"
    private var plugin: Plugin
    private var fileName: String
    var file: File
    var charset: String

    init {
        this.plugin = plugin
        this.fileName = fileName + EXTENSION
        this.file = File(Roulette.plugin.dataFolder, this.fileName)
        this.charset = "UTF-8"
    }

    fun toJson(data: RouletteHandler) {
        try {
            val writer = OutputStreamWriter(FileOutputStream(file), charset)
            val gson = GsonBuilder().create()
            gson.toJson(data, writer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun fromJson(): RouletteHandler? {
        try {
            val reader = InputStreamReader(FileInputStream(file), charset)
            val gson = Gson()
            return gson.fromJson(reader, RouletteHandler::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}