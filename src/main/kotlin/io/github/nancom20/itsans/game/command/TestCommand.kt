package io.github.nancom20.itsans.game.command

import io.github.nancom20.itsans.util.CreateItem
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.BlockDisplay
import org.bukkit.entity.Display
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.util.Transformation
import org.joml.AxisAngle4f
import org.joml.Vector3f

class TestCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as Player
        val world = player.world

        world.spawn(player.location, BlockDisplay::class.java) {
            it.block = Bukkit.createBlockData(Material.STONE)
            it.transformation = Transformation(
                Vector3f(0f, 2f, 0f),
                AxisAngle4f(0f, 0f, 0f, 0f),
                Vector3f(10f, 5f, 2f),
                AxisAngle4f(0f, 0f, 0f, 0f)
            )
        }

        return true
    }
}