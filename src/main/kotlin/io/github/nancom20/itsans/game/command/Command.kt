package io.github.nancom20.itsans.game.command

import io.github.nancom20.itsans.game.GameManager
import io.github.nancom20.itsans.game.Team
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Command: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (label == "team") {
            if (args[0] == "addTeam") {
                Team().addTeam(args[1])
            }

            if (args[0] == "addPlayer") {
                Bukkit.getPlayer(args[2])?.let { Team().addTeamPlayer(args[1], it) }
            }

            if (args[0] == "removeTeam") {
                Team().removeTeam(args[1])
            }

            if (args[0] == "removePlayer") {
                Bukkit.getPlayer(args[2])?.let { Team().removeTeamPlayer(args[1], it) }
            }

            if (args[0] == "start") {
                GameManager.game.start()
            }
        }
        return true
    }
}