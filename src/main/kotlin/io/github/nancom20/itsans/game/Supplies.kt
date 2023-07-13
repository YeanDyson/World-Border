package io.github.nancom20.itsans.game

import io.github.nancom20.itsans.util.CreateItem
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Chest
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionType

class Supplies(location: Location) {
    private val location: Location
    private val plugin = Bukkit.getPluginManager().getPlugin("itsnas")!!
    private val world = Bukkit.getWorld("world")!!
    private val suppliesItem = arrayOf(
        arrayOf(
            CreateItem().createItem(Material.NETHERITE_SWORD, ItemStack(Material.NETHERITE_SWORD).displayName(), 1, 1500, mapOf(Enchantment.VANISHING_CURSE to 1)),
            CreateItem().createPotionItem(PotionData(PotionType.INSTANT_HEAL), Component.text("힐템"), 1),
            CreateItem().createPotionItem(PotionData(PotionType.SPEED), Component.text("수피드 포션"), 1)
        ),
        arrayOf(
            CreateItem().createItem(Material.NETHERITE_CHESTPLATE, ItemStack(Material.NETHERITE_CHESTPLATE).displayName(), 1, 200, mapOf(Enchantment.VANISHING_CURSE to 1)),
            CreateItem().createPotionItem(PotionData(PotionType.INSTANT_HEAL), Component.text("힐템"), 1),
            CreateItem().createPotionItem(PotionData(PotionType.SPEED), Component.text("수피드 포션"), 1)
        ),
        arrayOf(
            CreateItem().createItem(Material.NETHERITE_BOOTS, ItemStack(Material.NETHERITE_BOOTS).displayName(), 1, 200, mapOf(Enchantment.VANISHING_CURSE to 1)),
            CreateItem().createPotionItem(PotionData(PotionType.INSTANT_HEAL), Component.text("힐템"), 1),
            CreateItem().createPotionItem(PotionData(PotionType.SPEED), Component.text("수피드 포션"), 1)
        ),
        arrayOf(
            CreateItem().createItem(Material.NETHERITE_HELMET, ItemStack(Material.NETHERITE_HELMET).displayName(), 1, 200, mapOf(Enchantment.VANISHING_CURSE to 1)),
            CreateItem().createPotionItem(PotionData(PotionType.INSTANT_HEAL), Component.text("힐템"), 1),
            CreateItem().createPotionItem(PotionData(PotionType.SPEED), Component.text("수피드 포션"), 1)
        ),
        arrayOf(
            CreateItem().createItem(Material.NETHERITE_LEGGINGS, ItemStack(Material.NETHERITE_LEGGINGS).displayName(), 1, 200, mapOf(Enchantment.VANISHING_CURSE to 1)),
            CreateItem().createPotionItem(PotionData(PotionType.INSTANT_HEAL), Component.text("힐템"), 1),
            CreateItem().createPotionItem(PotionData(PotionType.SPEED), Component.text("수피드 포션"), 1)
        ),
        arrayOf(
            CreateItem().createItem(Material.NETHERITE_INGOT, ItemStack(Material.NETHERITE_INGOT).displayName(), 1, enchants = mapOf(Enchantment.VANISHING_CURSE to 1)),
            CreateItem().createPotionItem(PotionData(PotionType.INSTANT_HEAL), Component.text("힐템"), 1),
            CreateItem().createPotionItem(PotionData(PotionType.SPEED), Component.text("수피드 포션"), 1)
        )
    )

    init {
        this.location = location
        spawn()
    }

    private fun spawn() {
        val block = world.getBlockAt(location)
        block.blockData = Bukkit.createBlockData(Material.CHEST)
        val chest = block.state as Chest

        Bukkit.broadcast(Component.text("보급 스폰됨: ${location.x} ${location.y} ${location.z}"))

        chest.inventory.contents = suppliesItem.random()
    }
}