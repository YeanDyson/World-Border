package io.github.nancom20.itsans.util

import org.bukkit.Bukkit

class TimeUtil {
    private val plugin = Bukkit.getPluginManager().getPlugin("itsnas")!!

    fun runTimerEnds(time: Int, runnable: Runnable) {
        var taskId = 0
        var timer = time.toInt()
        taskId = plugin.server.scheduler.scheduleSyncRepeatingTask(plugin, {
            timer--
            if (timer == 0) {
                runnable.run()
                plugin.server.scheduler.cancelTask(taskId)
            }
        }, 20L, 20L)
    }

    fun runPeriodicTimer(time: Int, runnable: Runnable) {
        var taskId = 0
        var timer = time.toInt()
        taskId = plugin.server.scheduler.scheduleSyncRepeatingTask(plugin, {
            runnable.run()
            timer--
            if (timer == 0) {
                plugin.server.scheduler.cancelTask(taskId)
            }
        }, 20L, 20L)
    }

    fun runTimer(time: Int, runnable: Runnable, endRunnable: Runnable) {
        var taskId = 0
        var timer = time.toInt()
        taskId = plugin.server.scheduler.scheduleSyncRepeatingTask(plugin, {
            runnable.run()
            timer--
            if (timer == 0) {
                endRunnable.run()
                plugin.server.scheduler.cancelTask(taskId)
            }
        }, 20L, 20L)
    }
}