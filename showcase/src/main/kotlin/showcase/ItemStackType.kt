package showcase

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.KtConfigSection
import dev.s7a.spigot.config.KtConfigValueType
import dev.s7a.spigot.config.getUnsafe
import dev.s7a.spigot.config.setUnsafe
import dev.s7a.spigot.config.type.value
import org.bukkit.inventory.ItemStack

// CODE-SNIPPET BEGIN
/**
 * シリアライザーを利用して [ItemStack] をコンフィグに格納する
 */
fun KtConfigSection.itemStackValue(path: String) = value(path, ItemStackType)

/**
 * シリアライザーを利用して [ItemStack] をコンフィグに格納する
 */
object ItemStackType : KtConfigValueType<ItemStack> {
    override fun get(config: KtConfig, path: String): KtConfigResult<ItemStack> {
        return config.getUnsafe(path)
    }

    override fun set(config: KtConfig, path: String, value: ItemStack?) {
        config.setUnsafe(path, value)
    }
}
// CODE-SNIPPET END
