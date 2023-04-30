package pro.freeserver.plugin.alphakun.roulette.consts

import org.bukkit.Material

class PrizeData(material: Material, odds: Double) {
    var material: Material
    var odds: Double
    init {
        this.material = material
        this.odds = odds
    }
}