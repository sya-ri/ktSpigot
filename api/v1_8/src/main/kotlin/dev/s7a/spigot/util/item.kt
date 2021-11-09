@file:JvmName("Item8")

package dev.s7a.spigot.util

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * [ItemMeta.hasDisplayName] が true ならば [ItemMeta.getDisplayName] を返す
 *
 * @since 1.0.0
 */
val ItemMeta.displayNameOrNull
    get() = if (hasDisplayName()) displayName else null

/**
 * [ItemMeta.hasLore] が true ならば [ItemMeta.getLore] を返す
 *
 * @since 1.0.0
 */
val ItemMeta.loreOrNull: List<String>?
    get() = if (hasLore()) lore else null

/**
 * [ItemMeta] を変更する
 *
 * @param action [ItemMeta] の変更処理
 * @return [ItemMeta] を変更できたら true
 * @since 1.0.0
 */
inline fun ItemStack.editItemMeta(action: ItemMeta.() -> Unit): Boolean {
    itemMeta = itemMeta?.apply(action) ?: return false
    return true
}

/**
 * [ItemStack.getItemMeta] が [T] ならば [ItemMeta] を変更する
 *
 * @param T [ItemMeta] の型
 * @param action [ItemMeta] の変更処理
 * @return [ItemMeta] を変更できたら true
 * @since 1.0.0
 */
@JvmName("editItemMetaT")
inline fun <reified T : ItemMeta> ItemStack.editItemMeta(action: T.() -> Unit): Boolean {
    return editItemMeta {
        if (this !is T) return false
        action()
    }
}
