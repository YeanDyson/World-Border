package io.github.nancom20.itsans.game

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class KillPlayer: Listener {
    private val plugin = Bukkit.getPluginManager().getPlugin("itsnas")!!
    private val logger = plugin.logger

    @EventHandler
    fun onPlayerKill(e: PlayerDeathEvent) {
        val player = e.player
        if (!GameManager.isSurvival(player)) {
            return
        }

        e.isCancelled = true
        GameManager.killPlayer(player)
        Bukkit.broadcast(Component.text("${player.name} 탈락"))
        Bukkit.broadcast(Component.text("남은 분대 ${GameManager.getSurvivalTeam()?.size}"))
        Bukkit.broadcast(Component.text("남은 플레이어 ${GameManager.getSurvivalPlayer()?.size}"))
        Team().getTeams().forEach{team ->
            if (!team.hasPlayer(player)) {
                return@forEach
            }

            team.entries.forEach {entries ->
                val entriesPlayer = Bukkit.getPlayer(entries)
                if (entriesPlayer?.let { it1 -> GameManager.isSurvival(it1) }!!) {
                    return
                }
            }
            GameManager.outTeam(team)
            Bukkit.broadcast(team.prefix().append(Component.text(" 전원 탈락")))
            Bukkit.broadcast(Component.text("남은 분대 ${GameManager.getSurvivalTeam()?.size}"))
            Bukkit.broadcast(Component.text("남은 플레이어 ${GameManager.getSurvivalPlayer()?.size}"))
            logger.info("${LegacyComponentSerializer.legacySection().serialize(team.prefix())} 탈락")
        }
    }
}