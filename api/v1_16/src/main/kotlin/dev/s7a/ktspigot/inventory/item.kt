@file:JvmName("Item16")

package dev.s7a.ktspigot.inventory

import dev.s7a.ktspigot.item.itemStack
import dev.s7a.ktspigot.util.color
import org.bukkit.Material

/**
 * アイテムを配置する
 *
 * @param index 配置するスロット
 * @param type マテリアル
 * @param amount アイテム数 / 1
 * @param displayName 表示名 / null
 * @param lore 説明文 / null
 * @param customModelData カスタムモデルデータ / null
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
    customModelData: Int? = null,
    altColorChar: Char = '&',
    action: KtInventoryClickEventHandler? = null
) {
    item(index, itemStack(type, amount, displayName, lore, customModelData, altColorChar), action)
}

/**
 * アイテムを配置する
 *
 * @param index 配置するスロット
 * @param type マテリアル
 * @param amount アイテム数 / 1
 * @param displayName 表示名 / null
 * @param lore 説明文 / null
 * @param customModelData カスタムモデルデータ / null
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
    customModelData: Int? = null,
    altColorChar: Char = '&',
    action: KtInventoryClickEventHandler? = null
) {
    item(index, itemStack(type, amount, displayName, lore, customModelData, altColorChar), action)
}

/**
 * アイテムを配置する
 *
 * @param index 配置するスロット
 * @param type マテリアル
 * @param amount アイテム数 / 1
 * @param displayName 表示名 / null
 * @param lore 説明文 / null
 * @param customModelData カスタムモデルデータ / null
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
    customModelData: Int? = null,
    altColorChar: Char = '&',
    action: KtInventoryClickEventHandler? = null
) {
    item(index, itemStack(type, amount, displayName, lore, customModelData, altColorChar), action)
}
