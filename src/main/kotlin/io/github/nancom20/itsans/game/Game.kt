package io.github.nancom20.itsans.game

import io.github.nancom20.itsans.game.worldBorder.WorldBorderField
import io.github.nancom20.itsans.util.TimeUtil
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.block.Biome
import kotlin.random.Random

class Game {
    private val world = Bukkit.getWorld("world")!!
    private val plugin = Bukkit.getPluginManager().getPlugin("itsnas")!!

    private val timeUtil = TimeUtil()
    private val worldBorderSize: Array<Int> = arrayOf(4000, 2000, 1000, 500, 100)

    fun start() {
        if (GameManager.isGame) {
            throw Exception("already started the game")
        }

        var time = 3
        timeUtil.runTimer(3, {
                Bukkit.getOnlinePlayers().forEach {
                    val title = Title.title(Component.text(time).color(NamedTextColor.RED), Component.text(" "))
                    it.showTitle(title)
                }
                time--
            }
        ) { startGame() }
    }

    private fun startGame() {

        Team().getTeams().forEach {teams ->
            val location = randomGround(-2000, 2000, -2000, 2000)
            GameManager.addTeam(teams)
            teams.entries.forEach {
                Bukkit.getPlayer(it)!!.teleport(location)
                GameManager.addPlayer(Bukkit.getPlayer(it)!!)
            }
        }

        world.worldBorder.damageAmount = 0.0

        timeUtil.runTimerEnds(480) {
            Bukkit.broadcast(Component.text("5분남"))
            timeUtil.runTimerEnds(120) {
                Bukkit.broadcast(Component.text("3분후 자기장"))
                world.pvp = true
                startWorldBorder(0)
            }
        }
    }

    private fun startWorldBorder(lastWorldBorderSize: Int) {
        val nextWorldBorderSize = lastWorldBorderSize + 1

        try {
            var worldBorder: WorldBorderField? = null

            worldBorder = WorldBorderField(worldBorderSize[nextWorldBorderSize], worldBorderSize[lastWorldBorderSize]) {
                Bukkit.broadcast(Component.text("자기장 끝"))
                Bukkit.broadcast(Component.text("3분후 자기장 시작"))
                startWorldBorder(nextWorldBorderSize)

                spawnSupplies(worldBorder!!)
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            WorldBorderField(50, worldBorderSize[lastWorldBorderSize])
        }
    }

    private fun spawnSupplies(worldBorderField: WorldBorderField) {
        val vertex = worldBorderField.getVertex()
        val minX = vertex.first.first.toInt()
        val maxX = vertex.first.second.toInt()
        val minZ = vertex.second.first.toInt()
        val maxZ = vertex.second.second.toInt()

        for (i in 1..3) {
            Supplies(randomGround(minX, maxX, minZ, maxZ))
        }
    }

    private fun endGame() {

    }

    private fun randomGround(minX: Int, maxX: Int, minZ: Int, maxZ: Int): Location {
        var targetLocation: Location
        do {
            val x = Random.nextInt(minX, maxX + 1)
            val z = Random.nextInt(minZ, maxZ + 1)
            val y = world.getHighestBlockYAt(x, z)
            targetLocation = Location(world, x.toDouble(), y.toDouble(), z.toDouble())
        } while (isOceanBiome(targetLocation.block.biome))

        targetLocation.y += 1.0

        return targetLocation
    }

    private fun isOceanBiome(biome: Biome): Boolean {
        return biome == Biome.DEEP_OCEAN || biome == Biome.FROZEN_OCEAN || biome == Biome.DEEP_FROZEN_OCEAN ||
                biome == Biome.COLD_OCEAN || biome == Biome.DEEP_COLD_OCEAN || biome == Biome.LUKEWARM_OCEAN ||
                biome == Biome.DEEP_LUKEWARM_OCEAN || biome == Biome.WARM_OCEAN || biome == Biome.OCEAN
    }
}