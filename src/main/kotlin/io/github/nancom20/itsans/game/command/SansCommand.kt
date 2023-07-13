package io.github.nancom20.itsans.game.command

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.minecraft.network.Connection
import net.minecraft.network.protocol.PacketFlow
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.level.ServerPlayerGameMode
import net.minecraft.server.network.ServerGamePacketListenerImpl
import net.minecraft.world.level.GameType
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import java.util.*

class SansCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val location = (sender as Player).location
        val craftPlayer = sender as CraftPlayer
        val minecartSever = craftPlayer.handle.server
        val worldServer = (craftPlayer.world as CraftWorld).handle
        val fakeConnection = Connection(PacketFlow.CLIENTBOUND)
        val playerProfile = (sender as Player).playerProfile
        val gameProfile = GameProfile(UUID.randomUUID(), "fakePlayer")
        gameProfile.properties.put("textures", Property("textures", playerProfile.textures.skin.toString()))
        val serverPlayer = ServerPlayer(minecartSever, worldServer, gameProfile).apply {
            setPos(location.x, location.y, location.z)
            connection = ServerGamePacketListenerImpl(minecartSever, fakeConnection, this)
            ServerPlayerGameMode(this).changeGameModeForPlayer(GameType.SURVIVAL)

        }

        worldServer.addNewPlayer(serverPlayer)
//        minecartSever.playerList.placeNewPlayer(fakeConnection, serverPlayer)

        serverPlayer.isNoGravity = false

        Bukkit.getOnlinePlayers().forEach {
            val connection = (it as CraftPlayer).handle.connection
            connection.send(ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, serverPlayer))
            connection.send(ClientboundAddPlayerPacket(serverPlayer))
        }

        println("실행됨")

        return true
    }
}