package io.github.nancom20.itsans.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.potion.PotionData

class CreateItem {

    fun createItem(material: Material, name: Component, amount: Int = 1
                   , damage: Int? = null, enchants: Map<Enchantment, Int>? = null, vararg lore: Component?): ItemStack {
        val item = ItemStack(material, amount)
        val metadata = item.itemMeta
        metadata.displayName(name)

        if (lore.isNotEmpty()) {
            metadata.lore(lore.toMutableList())
        }

        if (damage != null && metadata is Damageable) {
            metadata.damage = damage
        }

        enchants?.forEach { (enchantment, level) ->
            metadata.addEnchant(enchantment, level, true)
        }

        item.itemMeta = metadata
        return item
    }

    fun createPotionItem(potionData: PotionData, name: Component, amount: Int = 1, vararg lore: Component?): ItemStack {
        val potionItem = ItemStack(Material.POTION, amount)
        val potionMeta = potionItem.itemMeta as PotionMeta

        if (lore.isNotEmpty()) {
            potionMeta.lore(lore.toMutableList())
        }

        potionMeta.displayName(name)
        potionMeta.basePotionData = potionData

        potionItem.itemMeta = potionMeta

        return potionItem
    }

    fun createPlayerSkull(player: Player, amount: Int = 1, vararg lore: Component?): ItemStack {
        val playerSkull = ItemStack(Material.PLAYER_HEAD, amount)
        val skullMeta: SkullMeta = playerSkull.itemMeta as SkullMeta

        if (lore.isNotEmpty()) {
            skullMeta.lore(lore.toMutableList())
        }

        skullMeta.displayName(Component.text(player.name).color(NamedTextColor.GREEN))
        skullMeta.owningPlayer = player

        playerSkull.itemMeta = skullMeta

        return playerSkull
    }

}