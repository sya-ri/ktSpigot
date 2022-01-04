package dev.s7a.spigot

import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.LeatherArmorMeta

class TestLeatherArmorMeta(meta: ItemMeta?) : TestItemMeta(meta), LeatherArmorMeta {
    private var color: Color? = null

    init {
        if (meta is TestLeatherArmorMeta) {
            color = meta.color
        }
    }

    override fun clone(): LeatherArmorMeta {
        return TestLeatherArmorMeta(super.clone()).also {
            it.color = color
        }
    }

    override fun getColor(): Color {
        return color ?: Bukkit.getItemFactory().defaultLeatherColor
    }

    override fun setColor(color: Color?) {
        this.color = color
    }
}
