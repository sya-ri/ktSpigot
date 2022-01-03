package dev.s7a.spigot.config.formatter

import dev.s7a.spigot.config.KtConfigFormatter
import dev.s7a.spigot.location.BlockLocation
import org.bukkit.Bukkit

/**
 * デフォルトとして使用する [BlockLocation] のコンフィグフォーマッタ
 *
 * @see dev.s7a.spigot.config.blockLocationValue
 * @since 1.0.0
 */
object DefaultBlockLocationFormatter : KtConfigFormatter<BlockLocation> {
    override val name: String = this::class.java.simpleName

    override fun string(value: BlockLocation): String {
        return value.run {
            "${world?.name}, $x, $y, $z"
        }
    }

    override fun value(string: String): BlockLocation? {
        return string.split("\\s*,\\s*".toRegex()).let {
            when (it.size) {
                4 -> {
                    val world = Bukkit.getWorld(it[0])
                    val x = it[1].toIntOrNull() ?: return null
                    val y = it[2].toIntOrNull() ?: return null
                    val z = it[3].toIntOrNull() ?: return null
                    BlockLocation(world, x, y, z)
                }
                else -> {
                    null
                }
            }
        }
    }
}
