@file:Suppress("DEPRECATION", "unused", "FunctionName")

package dev.s7a.ktspigot.command

import org.bukkit.Material

/**
 * よく使う補完候補
 *
 * @see Literals
 * @since 1.0.0
 */
@Deprecated("Literals を使ってください")
object Literal {
    /**
     * マテリアル
     *
     * @see Material.values
     * @see Literals.Materials
     * @since 1.0.0
     */
    @Deprecated("Literals::Materials を使ってください", ReplaceWith("Literals.Materials"))
    val Materials = Literals.EnumNames<Material>()

    /**
     * ブロックマテリアル
     *
     * @see Material.values
     * @see Material.isBlock
     * @see Literals.Blocks
     * @since 1.0.0
     */
    @Deprecated("Literals::Blocks を使ってください", ReplaceWith("Literals.Blocks"))
    val Blocks = Literals.EnumNames(Material::isBlock)
}

/**
 * マテリアル
 *
 * @see Material.values
 * @since 1.0.0
 */
inline val Literals.Materials
    get() = Literal.Materials

/**
 * ブロックマテリアル
 *
 * @see Material.values
 * @see Material.isBlock
 * @since 1.0.0
 */
inline val Literals.Blocks
    get() = Literal.Blocks
