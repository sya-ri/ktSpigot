package showcase

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.KtConfigValueType
import dev.s7a.ktspigot.config.getUnsafe
import dev.s7a.ktspigot.config.setUnsafe
import dev.s7a.ktspigot.config.type.value
import org.bukkit.inventory.ItemStack

// CODE-SNIPPET BEGIN
/**
 * シリアライザーを利用して [ItemStack] をコンフィグに格納する
 */
fun KtConfigBase.itemStackValue(path: String) = value(path, ItemStackType)

/**
 * シリアライザーを利用して [ItemStack] をコンフィグに格納する
 */
object ItemStackType : KtConfigValueType<ItemStack> {
    override fun get(config: KtConfigBase, path: String): KtConfigResult<ItemStack> {
        return config.getUnsafe(path)
    }

    override fun set(config: KtConfigBase, path: String, value: ItemStack?) {
        config.setUnsafe(path, value)
    }
}
// CODE-SNIPPET END
