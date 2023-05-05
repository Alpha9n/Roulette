package pro.freeserver.plugin.alphakun.roulette.handlers

import pro.freeserver.plugin.alphakun.roulette.Roulette
import pro.freeserver.plugin.alphakun.roulette.consts.RouletteData
import pro.freeserver.plugin.alphakun.roulette.utils.GsonUtil
import java.util.*

class RouletteHandler {
    private var rouletteList: MutableList<RouletteData> = mutableListOf()

    fun addRoulette(roulette: RouletteData) {
        this.rouletteList.add(roulette)

    }

    fun saveRoulette() {
        GsonUtil("rouletteDataList", Roulette.plugin).toJson(this)
    }

    fun loadRoulette() {
        val rouletteHandler = GsonUtil("rouletteDataList", Roulette.plugin).fromJson()
        this.rouletteList = rouletteHandler?.rouletteList?: mutableListOf()
    }

    fun getRoulettes(name: String): MutableList<RouletteData>? {
        val result: MutableList<RouletteData> = mutableListOf()
        for(roulette in rouletteList) {
            if(roulette.name.equals(name, true)) {
                result.add(roulette)
            }
        }
        if (result.isEmpty()) return null
        return result
    }

    fun getRoulette(uuid: UUID): RouletteData? {
        for(roulette in rouletteList) {
            if(roulette.uuid == uuid) {
                return roulette
            }
        }
        return null
    }

    fun removeRoulette(uuid: UUID) {
        for(roulette in rouletteList) {
            if(roulette.uuid == uuid) {
                rouletteList.remove(roulette)
            }
        }
    }

    fun getRouletteList(): MutableList<RouletteData> {
        return rouletteList
    }
}