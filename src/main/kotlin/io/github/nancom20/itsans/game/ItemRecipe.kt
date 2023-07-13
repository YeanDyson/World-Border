package io.github.nancom20.itsans.game

import io.github.nancom20.itsans.util.CreateItem
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.RecipeChoice
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionType

class ItemRecipe {
    private val plugin = Bukkit.getPluginManager().getPlugin("itsnas")!!
    init {
        createRecip()
    }

    private fun createRecip() {
        val healPotionItem = CreateItem().createPotionItem(PotionData(PotionType.INSTANT_HEAL), Component.text("힐템"), 1)
        val healPotionRecip = ShapedRecipe(NamespacedKey(plugin, "healPotion"), healPotionItem).apply {
            shape(
                "111",
                "101",
                "111"
            )
            setIngredient('1', RecipeChoice.MaterialChoice(
                Material.ACACIA_LEAVES,
                Material.AZALEA_LEAVES,
                Material.BIRCH_LEAVES,
                Material.DARK_OAK_LEAVES,
                Material.JUNGLE_LEAVES,
                Material.FLOWERING_AZALEA_LEAVES,
                Material.MANGROVE_LEAVES,
                Material.OAK_LEAVES,
                Material.SPRUCE_LEAVES
            ))
            setIngredient('0', RecipeChoice.MaterialChoice(
                Material.DANDELION,
                Material.POPPY,
                Material.BLUE_ORCHID,
                Material.ALLIUM,
                Material.AZURE_BLUET,
                Material.ORANGE_TULIP,
                Material.PINK_TULIP,
                Material.RED_TULIP,
                Material.WHITE_TULIP,
                Material.CORNFLOWER,
                Material.LILY_OF_THE_VALLEY,
                Material.SUNFLOWER,
                Material.LILAC,
                Material.ROSE_BUSH,
                Material.PEONY,
                Material.OXEYE_DAISY
            ))
        }
        Bukkit.addRecipe(healPotionRecip)

        val jumpPotionItem = CreateItem().createPotionItem(PotionData(PotionType.JUMP, false, true  ), Component.text("점프 포션"), 1)
        val jumpPotionRecip = ShapedRecipe(NamespacedKey(plugin, "jumpPotion"), jumpPotionItem).apply {
            shape(
                "111",
                "101",
                "121"
            )
            setIngredient('0', Material.IRON_BOOTS)
            setIngredient('1', RecipeChoice.MaterialChoice(
                Material.ACACIA_LEAVES,
                Material.AZALEA_LEAVES,
                Material.BIRCH_LEAVES,
                Material.DARK_OAK_LEAVES,
                Material.JUNGLE_LEAVES,
                Material.FLOWERING_AZALEA_LEAVES,
                Material.MANGROVE_LEAVES,
                Material.OAK_LEAVES,
                Material.SPRUCE_LEAVES
            ))
            setIngredient('2', Material.IRON_INGOT)
        }
        Bukkit.addRecipe(jumpPotionRecip)

        val speedPotionItem = CreateItem().createPotionItem(PotionData(PotionType.SPEED), Component.text("수피드 포션"), 1)
        val speedPotionRecip = ShapedRecipe(NamespacedKey(plugin, "speedPotion"), speedPotionItem).apply {
            shape(
                "111",
                "101",
                "121"
            )
            setIngredient('0', Material.IRON_BOOTS)
            setIngredient('1', RecipeChoice.MaterialChoice(
                Material.ACACIA_LEAVES,
                Material.AZALEA_LEAVES,
                Material.BIRCH_LEAVES,
                Material.DARK_OAK_LEAVES,
                Material.JUNGLE_LEAVES,
                Material.FLOWERING_AZALEA_LEAVES,
                Material.MANGROVE_LEAVES,
                Material.OAK_LEAVES,
                Material.SPRUCE_LEAVES
            ))
            setIngredient('2', Material.COPPER_INGOT)
        }
        Bukkit.addRecipe(speedPotionRecip)

        val protectionBook = CreateItem().createItem(Material.ENCHANTED_BOOK, Component.text("인첸트된 책"), 1, enchants = mapOf(Enchantment.PROTECTION_ENVIRONMENTAL to 2))
        val protectionRecip = ShapedRecipe(NamespacedKey(plugin, "protectionPotion"), protectionBook).apply {
            shape(
                "123",
                "033",
                "333"
            )
            setIngredient('0', Material.IRON_CHESTPLATE)
            setIngredient('1', Material.BOOK)
            setIngredient('2', Material.IRON_INGOT)
        }
        Bukkit.addRecipe(protectionRecip)

        val sharpnessBook = CreateItem().createItem(Material.ENCHANTED_BOOK, Component.text("인첸트된 책"), 1, enchants = mapOf(Enchantment.DAMAGE_ALL to 2))
        val sharpnessRecip = ShapedRecipe(NamespacedKey(plugin, "sharpnessPotion"), sharpnessBook).apply {
            shape(
                "123",
                "033",
                "333"
            )
            setIngredient('0', Material.IRON_SWORD)
            setIngredient('1', Material.BOOK)
            setIngredient('2', Material.IRON_INGOT)
        }
        Bukkit.addRecipe(sharpnessRecip)
    }
}