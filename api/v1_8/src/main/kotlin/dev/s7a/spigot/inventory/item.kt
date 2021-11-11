@file:JvmName("Item8")

package dev.s7a.spigot.inventory

import dev.s7a.spigot.util.color
import dev.s7a.spigot.util.itemStack
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * アイテムを配置する
 *
 * @param index 配置するスロット
 * @param itemStack アイテム
 * @param action クリック処理 / null
 * @since 1.0.0
 */
fun KtInventory.item(index: Int, itemStack: ItemStack, action: ((InventoryClickEvent) -> Unit)? = null) {
    if (index in bukkitInventory.contents.indices) {
        bukkitInventory.setItem(index, itemStack)
        if (action != null) {
            actions[index] = action
        } else {
            actions.remove(index)
        }
    } else {
        throw IllegalArgumentException("指定されているインデックスがインベントリの範囲外です (index: $index)")
    }
}

/**
 * アイテムを配置する
 *
 * @param index 配置するスロット
 * @param itemStack アイテム
 * @param action クリック処理 / null
 * @since 1.0.0
 */
fun KtInventory.item(index: Iterable<Int>, itemStack: ItemStack, action: ((InventoryClickEvent) -> Unit)? = null) {
    index.forEach {
        item(it, itemStack, action)
    }
}

/**
 * アイテムを配置する
 *
 * @param index 配置するスロット
 * @param itemStack アイテム
 * @param action クリック処理 / null
 * @since 1.0.0
 */
fun KtInventory.item(index: IntRange, itemStack: ItemStack, action: ((InventoryClickEvent) -> Unit)? = null) {
    item(index.toList(), itemStack, action)
}

/**
 * アイテムを配置する
 *
 * @param index 配置するスロット
 * @param type マテリアル
 * @param amount アイテム数 / 1
 * @param displayName 表示名 / null
 * @param lore 説明文 / null
 * @param altColorChar [color] に使う文字 / '&'
 * @param action クリック処理 / null
 * @since 1.0.0
 */
fun KtInventory.item(
    index: Int,
    type: Material,
    amount: Int = 1,
    displayName: String? = null,
    lore: List<String>? = null,
    altColorChar: Char = '&',
    action: ((InventoryClickEvent) -> Unit)? = null,
) {
    item(index, itemStack(type, amount, displayName, lore, altColorChar), action)
}

/**
 * アイテムを配置する
 *
 * @param index 配置するスロット
 * @param type マテリアル
 * @param amount アイテム数 / 1
 * @param displayName 表示名 / null
 * @param lore 説明文 / null
 * @param altColorChar [color] に使う文字 / '&'
 * @param action クリック処理 / null
 * @since 1.0.0
 */
fun KtInventory.item(
    index: IntRange,
    type: Material,
    amount: Int = 1,
    displayName: String? = null,
    lore: List<String>? = null,
    altColorChar: Char = '&',
    action: ((InventoryClickEvent) -> Unit)? = null,
) {
    item(index, itemStack(type, amount, displayName, lore, altColorChar), action)
}

/**
 * アイテムを配置する
 *
 * @param index 配置するスロット
 * @param type マテリアル
 * @param amount アイテム数 / 1
 * @param displayName 表示名 / null
 * @param lore 説明文 / null
 * @param altColorChar [color] に使う文字 / '&'
 * @param action クリック処理 / null
 * @since 1.0.0
 */
fun KtInventory.item(
    index: List<Int>,
    type: Material,
    amount: Int = 1,
    displayName: String? = null,
    lore: List<String>? = null,
    altColorChar: Char = '&',
    action: ((InventoryClickEvent) -> Unit)? = null,
) {
    item(index, itemStack(type, amount, displayName, lore, altColorChar), action)
}
