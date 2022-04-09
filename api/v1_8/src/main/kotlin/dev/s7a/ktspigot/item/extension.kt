@file:JvmName("Item8")

package dev.s7a.ktspigot.item

import dev.s7a.ktspigot.util.color
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * [ItemMeta.hasDisplayName] が true ならば [ItemMeta.getDisplayName] を返す
 *
 * @see ItemMeta.displayNameOrNull
 * @since 1.0.0
 */
val ItemStack.displayNameOrNull
    get() = itemMeta?.displayNameOrNull

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
 * @see ItemMeta.loreOrNull
 * @since 1.0.0
 */
val ItemStack.loreOrNull
    get() = itemMeta?.loreOrNull

/**
 * [ItemMeta.hasLore] が true ならば [ItemMeta.getLore] を返す
 *
 * @since 1.0.0
 */
val ItemMeta.loreOrNull: List<String>?
    get() = if (hasLore()) lore else null

/**
 * [ItemMeta] を変更する処理
 *
 * @since 1.0.0
 */
typealias ItemMetaEditAction = ItemMeta.() -> Unit

/**
 * [ItemMeta] を変更する
 *
 * @param action [ItemMeta] の変更処理
 * @return [ItemMeta] を変更できたら true
 * @since 1.0.0
 */
inline fun ItemStack.editItemMeta(action: ItemMetaEditAction): Boolean {
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

/**
 * アイテムの説明文を変更する
 *
 * @param altColorChar [color] に使う文字 / '&'
 * @param action 説明文の変更処理
 * @since 1.0.0
 */
inline fun ItemMeta.editLore(altColorChar: Char? = '&', action: MutableList<String>.() -> Unit) {
    lore = loreOrNull.orEmpty().toMutableList().apply(action).color(altColorChar)
}

/**
 * アイテムの説明文が存在するならば変更する
 *
 * @param altColorChar [color] に使う文字 / '&'
 * @param action 説明文の変更処理
 * @return 説明文を変更できたら true
 * @since 1.0.0
 */
inline fun ItemMeta.editLoreIfHas(altColorChar: Char? = '&', action: MutableList<String>.() -> Unit): Boolean {
    lore = loreOrNull?.toMutableList()?.apply(action)?.color(altColorChar) ?: return false
    return true
}

/**
 * [ItemStack] を生成する
 *
 * @param type マテリアル
 * @param amount アイテム数 / 1
 * @param editMetaAction [ItemMeta] の変更処理
 * @return [ItemStack]
 * @since 1.0.0
 */
inline fun itemStack(
    type: Material,
    amount: Int = 1,
    editMetaAction: ItemMetaEditAction,
): ItemStack {
    return ItemStack(type, amount).apply {
        editItemMeta(editMetaAction)
    }
}

/**
 * [ItemStack] を生成する
 *
 * @param type マテリアル
 * @param amount アイテム数 / 1
 * @param displayName 表示名 / null
 * @param lore 説明文 / null
 * @param altColorChar [color] に使う文字 / '&'
 * @param editMetaAction [ItemMeta] の変更処理 / null
 * @return [ItemStack]
 * @since 1.0.0
 */
fun itemStack(
    type: Material,
    amount: Int = 1,
    displayName: String? = null,
    lore: List<String>? = null,
    altColorChar: Char? = '&',
    editMetaAction: ItemMetaEditAction? = null,
): ItemStack {
    return itemStack(type, amount) {
        setDisplayName(displayName?.color(altColorChar))
        setLore(lore?.color(altColorChar))
        editMetaAction?.invoke(this)
    }
}
