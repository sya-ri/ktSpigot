package dev.s7a.spigot

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemFactory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class TestItemFactory : ItemFactory {
    private val defaultLeatherColor = Color.fromRGB(10511680)

    override fun equals(meta1: ItemMeta?, meta2: ItemMeta?): Boolean {
        return meta1 == meta2
    }

    private fun getItemMeta(material: Material, meta: ItemMeta?): ItemMeta? {
        return when (material) {
            Material.AIR -> null
            Material.LEATHER_BOOTS -> TestLeatherArmorMeta(meta)
            else -> TestItemMeta(meta)
        }
    }

    override fun getItemMeta(material: Material): ItemMeta? {
        return getItemMeta(material, null)
    }

    override fun isApplicable(meta: ItemMeta?, stack: ItemStack?): Boolean {
        return isApplicable(meta, stack?.type)
    }

    override fun isApplicable(meta: ItemMeta?, material: Material?): Boolean {
        if (meta == null || material == null) return false
        return material != Material.AIR
    }

    override fun asMetaFor(meta: ItemMeta, stack: ItemStack): ItemMeta? {
        return asMetaFor(meta, stack.type)
    }

    override fun asMetaFor(meta: ItemMeta, material: Material): ItemMeta? {
        return getItemMeta(material, meta)
    }

    override fun getDefaultLeatherColor(): Color {
        return defaultLeatherColor
    }

    override fun updateMaterial(meta: ItemMeta, material: Material): Material {
        return material
    }
}
