package io.github.nancom20.itsans.game.gui

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot

class InformationGui {
    private var player: Player? = null

    fun setInformationGui(player: Player) {
        this.player = player
        createScoreBoard()
    }

    private fun createScoreBoard() {
        val board = Bukkit.getScoreboardManager().newScoreboard
        val obj = board.registerNewObjective("Information", "sans", Component.text("sanss"))
        obj.displaySlot = DisplaySlot.SIDEBAR

        val onlineName = obj.getScore(ChatColor.GREEN.toString() + "world border")
        onlineName.score = 15

        val onlineCounter = board.registerNewTeam("worldBorder")
        onlineCounter.addEntry(ChatColor.BLACK.toString() + "" + ChatColor.WHITE.toString());
        board.getTeam("worldBorder")!!.prefix(Component.text("0분 0초"))
        obj.getScore(ChatColor.BLACK.toString() + "" + ChatColor.WHITE.toString()).score = 14;
        player!!.scoreboard = board;
    }
}