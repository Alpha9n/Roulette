package pro.freeserver.plugin.alphakun.roulette.handlers

import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.TextDisplay
import org.bukkit.persistence.PersistentDataType
import pro.freeserver.plugin.alphakun.roulette.Roulette
import pro.freeserver.plugin.alphakun.roulette.Roulette.Companion.rouletteHandler
import pro.freeserver.plugin.alphakun.roulette.consts.PrizeData
import pro.freeserver.plugin.alphakun.roulette.consts.RouletteData
import java.util.UUID

class SummonHandler(name: String, list: MutableList<PrizeData>) {
    var name: String
    var list: MutableList<PrizeData>
    val uuid: UUID

    init {
        this.name = name
        this.list = list
        this.uuid = UUID.randomUUID()
    }

    fun summonRouletteSet(location: Location) {

        // add roulette data to list
        rouletteHandler.addRoulette(RouletteData(name, uuid, list, location))

        // summon title
        val textDisplay = location.world.spawnEntity(Location(location.world, location.x, location.y + 3, location.z), EntityType.TEXT_DISPLAY)
        if (textDisplay is TextDisplay) {
            textDisplay.text(Component.text(name))
            textDisplay.persistentDataContainer.set(Roulette.rouletteTag, PersistentDataType.STRING, name)
        }
        summonRoulette(location)
    }

    private fun summonRoulette(location: Location) {

    }
}