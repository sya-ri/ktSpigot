@file:JvmName("Item11")

package dev.s7a.ktspigot.item

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * [ItemMeta.hasLocalizedName] が true ならば [ItemMeta.getLocalizedName] を返す
 *
 * @see ItemMeta.localizedNameOrNull
 * @since 1.0.0
 */
val ItemStack.localizedNameOrNull
    get() = itemMeta?.localizedNameOrNull

/**
 * [ItemMeta.hasLocalizedName] が true ならば [ItemMeta.getLocalizedName] を返す
 *
 * @since 1.0.0
 */
val ItemMeta.localizedNameOrNull
    get() = if (hasLocalizedName()) localizedName else null
