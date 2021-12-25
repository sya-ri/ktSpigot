@file:JvmName("Item16")

package dev.s7a.spigot.item

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * [ItemMeta.hasCustomModelData] が true ならば [ItemMeta.getCustomModelData] を返す
 *
 * @since 1.0.0
 */
val ItemMeta.customModelDataOrNull
    get() = if (hasCustomModelData()) customModelData else null

/**
 * [ItemStack] を生成する
 *
 * @param type マテリアル
 * @param amount アイテム数 / 1
 * @param displayName 表示名 / null
 * @param lore 説明文 / null
 * @param customModelData カスタムモデルデータ / null
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
    customModelData: Int? = null,
    altColorChar: Char? = '&',
    editMetaAction: ItemMetaEditAction? = null,
): ItemStack {
    return itemStack(type, amount, displayName, lore, altColorChar) {
        setCustomModelData(customModelData)
        editMetaAction?.invoke(this)
    }
}
