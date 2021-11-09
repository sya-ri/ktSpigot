@file:JvmName("Item16")

package dev.s7a.spigot.util

import org.bukkit.inventory.meta.ItemMeta

/**
 * [ItemMeta.hasCustomModelData] が true ならば [ItemMeta.getCustomModelData] を返す
 *
 * @since 1.0.0
 */
val ItemMeta.customModelDataOrNull
    get() = if (hasCustomModelData()) customModelData else null
