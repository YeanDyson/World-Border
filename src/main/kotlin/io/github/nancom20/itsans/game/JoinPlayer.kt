package io.github.nancom20.itsans.game

import io.github.nancom20.itsans.game.gui.InformationGui
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinPlayer: Listener {

    @EventHandler
    fun onJoinPlayer(e: PlayerJoinEvent) {
//        InformationGui().setInformationGui(e.player)
    }
}