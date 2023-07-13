package io.github.nancom20.itsans

import io.github.nancom20.itsans.game.command.Command
import io.github.nancom20.itsans.game.JoinPlayer
import io.github.nancom20.itsans.game.ItemRecipe
import io.github.nancom20.itsans.game.KillPlayer
import io.github.nancom20.itsans.game.command.SansCommand
import io.github.nancom20.itsans.game.command.TestCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    override fun onEnable() {
        logger.info("sans on")

        getCommand("team")?.setExecutor(Command())
        getCommand("aaa")?.setExecutor(TestCommand())
        getCommand("sans")?.setExecutor(SansCommand())

        Bukkit.getPluginManager().registerEvents(JoinPlayer(), this)
        Bukkit.getPluginManager().registerEvents(KillPlayer(), this)

        ItemRecipe()
    }

    override fun onDisable() {
        logger.info("sans off")
    }
}