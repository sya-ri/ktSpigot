@file:JvmName("Item11")

package dev.s7a.spigot.item

import org.bukkit.inventory.meta.ItemMeta

/**
 * [ItemMeta.hasLocalizedName] が true ならば [ItemMeta.getLocalizedName] を返す
 *
 * @since 1.0.0
 */
val ItemMeta.localizedNameOrNull
    get() = if (hasLocalizedName()) localizedName else null
