package dev.s7a.ktspigot.config.type

import org.bukkit.Material

/**
 * [Material] のコンフィグデータ型
 *
 * @param ignoreCase 大文字小文字を無視する
 * @see materialValue
 * @since 1.0.0
 */
class MaterialType(ignoreCase: Boolean) : EnumType.Name<Material>(Material::class.java, ignoreCase) {
    override val nameToEnum = { name: String ->
        Material.getMaterial(if (ignoreCase) name.uppercase() else name)
    }
}
