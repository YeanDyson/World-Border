package io.github.nancom20.itsans.game.worldBorder

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import java.math.BigDecimal
import kotlin.random.Random

class WorldBorderField(to: Int, from: Int, endRunnable: Runnable? = null) {
    private val world = Bukkit.getWorld("world")!!
    private val worldBorder = world.worldBorder
    private val plugin = Bukkit.getPluginManager().getPlugin("itsnas")!!
    private val scheduler = Bukkit.getScheduler()
    private var endRunnable: Runnable?

    private var fromSize = 0
    private var toSize = 0
    private var canterX = 0.0
    private var canterZ = 0.0

    init {
        this.fromSize = from
        this.toSize = to
        this.endRunnable = endRunnable
        worldBorder.size = to.toDouble()

        borderRandom()
    }

    private fun borderRandom() {
        canterX = (Random.nextInt(-((fromSize - toSize) / 2), ((fromSize - toSize) / 2) + 1) + worldBorder.center.x.toInt()).toDouble()
        canterZ = (Random.nextInt(-((fromSize - toSize) / 2), ((fromSize - toSize) / 2) + 1) + worldBorder.center.y.toInt()).toDouble()
        move()
    }

    private fun move() {
        val speed = 4.0
        val duration = (fromSize - toSize) / speed

        val (canterXSpeed, canterYSpeed) = canterSpeed(worldBorder.center, Location(world, canterX, 0.0, canterZ), duration.toInt())
        var timer = duration.toInt()
        var tick = 0
        var size = fromSize
        var taskId = 0

        plugin.logger.info("formCanter: ${worldBorder.center.x}, ${worldBorder.center.z} toCanter: $canterX, $canterZ")
        plugin.logger.info("vertex: ${-((fromSize - toSize) / 2) + canterX}, ${((fromSize - toSize) / 2) + canterX}" +
                "${-((fromSize - toSize) / 2) + canterZ}, ${((fromSize - toSize) / 2) + canterZ}")
        plugin.logger.info("formSize: $fromSize toSize: $toSize")
        plugin.logger.info("duration: $duration {${(duration/60).toInt()}분 ${duration%60}초}")
        plugin.logger.info("$canterXSpeed, $canterYSpeed")

        Bukkit.broadcast(Component.text("안전구역 X: ${-((fromSize - toSize) / 2) + canterX}, ${((fromSize - toSize) / 2) + canterX}, \n Z: ${-((fromSize - toSize) / 2) + canterZ}, ${((fromSize - toSize) / 2) + canterZ}"))
        Bukkit.broadcast(Component.text("자기장 시작"))

        taskId = plugin.server.scheduler.scheduleSyncRepeatingTask(plugin, {
            if (timer == 0) {
                plugin.server.scheduler.cancelTask(taskId)
                worldBorder.center = Location(world, canterX, 0.0, canterZ)
                endRunnable?.run()

                return@scheduleSyncRepeatingTask
            }

            if (tick == 4) {
                timer --
                tick = 0
                worldBorder.center = Location(world, BigDecimal("${worldBorder.center.x}").add(BigDecimal("$canterXSpeed")).toDouble(), 0.0, BigDecimal("${worldBorder.center.z}").add(BigDecimal("$canterYSpeed")).toDouble())
                Bukkit.getOnlinePlayers().forEach {
                    val board = it.scoreboard
                    plugin.logger.info("${(timer/60)}분 ${timer%60}초")
//                    board.getTeam("worldBorder")!!.prefix(Component.text("${(timer/60)}분 ${timer%60}초"))
                }
            }
            size--
            worldBorder.size = size.toDouble()

            tick++
        }, 0L, 5L)
    }

    private fun canterSpeed(from: Location, to: Location, timeTaken: Int): Pair<Double, Double> {
        val xSpeed = -((from.x - to.x) / timeTaken)
        val zSpeed = -((from.z - to.z) / timeTaken)

        return Pair(xSpeed, zSpeed)
    }

    // <minX, maxX> <minZ, maxZ>
    fun getVertex(): Pair<Pair<Double, Double>, Pair<Double, Double>> = Pair(
        Pair(-((fromSize - toSize) / 2) + canterX, ((fromSize - toSize) / 2) + canterX),
        Pair(-((fromSize - toSize) / 2) + canterZ, ((fromSize - toSize) / 2) + canterZ)
    )
}