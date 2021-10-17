package dev.s7a.spigot.command

import org.bukkit.Material

/**
 * よく使う補完候補
 *
 * @see KtCommandTabCompleterTree.literal
 * @since 1.0.0
 */
object Literals {
    /**
     * マテリアル
     *
     * @see Material.values
     * @since 1.0.0
     */
    val Materials = Material.values().map(Material::name)

    /**
     * ブロックマテリアル
     *
     * @see Material.values
     * @see Material.isBlock
     * @since 1.0.0
     */
    val Blocks = Material.values().filter(Material::isBlock).map(Material::name)
}
