package io.github.nancom20.itsans.game

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Team

object GameManager {
    val game = Game()

    var isGame = false

    private val gamePlayer: ArrayList<Player> = arrayListOf()
    private val gameTeam: ArrayList<Team> = arrayListOf()

    fun isSurvival(player: Player): Boolean = gamePlayer.contains(player)

    fun getSurvivalPlayer(): Array<out Any>? = gamePlayer.toArray()

    fun getSurvivalTeam(): Array<out Any>? = gameTeam.toArray()

    fun addTeam(team: Team) {
        gameTeam.add(team)
    }

    fun addPlayer(player: Player) {
        gamePlayer.add(player)
    }

    fun killPlayer(player: Player) {
        player.gameMode = GameMode.SPECTATOR
        gamePlayer.remove(player)
    }

    fun outTeam(team: Team) {
//        team.entries.forEach { entries ->
//            Bukkit.getPlayer(entries)?.kick(Component.text("팀 탈락 "))
//        }
        gameTeam.remove(team)
    }
}