package io.github.nancom20.itsans.game

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Team


class Team {
    private val scoreboard = Bukkit.getScoreboardManager().mainScoreboard

    fun addTeam(name: String) {
        val team = scoreboard.registerNewTeam(name)
        team.prefix(Component.text("[$name]").color(NamedTextColor.GREEN))
        team.suffix(Component.text("").color(NamedTextColor.WHITE))
        team.setAllowFriendlyFire(false)
    }

    fun removeTeam(name: String) {
        scoreboard.getTeam(name)?.unregister()
    }

    fun addTeamPlayer(name: String, player: Player) {
        player.displayName(Component.text("[$name]").color(NamedTextColor.GREEN).append(Component.text(player.name).color(NamedTextColor.WHITE)))
        scoreboard.getTeam(name)?.addPlayer(player)

        player.inventory
    }

    fun removeTeamPlayer(name: String, player: Player) {
        player.displayName(Component.text(player.name))
        player.playerListName(Component.text(player.name))
        scoreboard.getTeam(name)?.removePlayer(player)
    }

    fun getTeams(): Collection<Team> = scoreboard.teams
}