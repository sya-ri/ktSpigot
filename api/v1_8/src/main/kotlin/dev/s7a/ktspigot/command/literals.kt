@file:Suppress("unused", "FunctionName")

package dev.s7a.ktspigot.command

import org.bukkit.Material
import org.bukkit.command.CommandSender

/**
 * マテリアル
 *
 * @see Material.values
 * @since 1.0.0
 */
fun KtCommandTabCompleterTree<CommandSender>.materialNames(child: KtCommandTabCompleteBuilder<CommandSender>? = null) {
    literal(Material.values().map(Material::name), child)
}
