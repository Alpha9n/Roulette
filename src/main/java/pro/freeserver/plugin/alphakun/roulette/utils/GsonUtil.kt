package pro.freeserver.plugin.alphakun.roulette.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.bukkit.plugin.Plugin
import pro.freeserver.plugin.alphakun.roulette.consts.RouletteData
import sun.tools.jconsole.inspector.Utils.getClass
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class GsonUtil<T>(fileName: String, plugin: Plugin) {
    private var gson: Gson = Gson()
    private var plugin: Plugin
    private var fileName: String
    var file: File
    var charset: Charset
    init {
        this.plugin = plugin
        this.fileName = fileName
        this.file = File(plugin.dataFolder, fileName)
        this.charset = StandardCharsets.UTF_8
    }

    fun toJson(data: T) {
        try {
            OutputStreamWriter(FileOutputStream(file), charset).use { writer ->
                gson.toJson(data, writer)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun fromJson(): T? {
        try {
            InputStreamReader(FileInputStream(file), charset).use { reader ->
                return gson.fromJson(reader, object : TypeToken<T>() {}.type)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}