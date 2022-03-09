package dev.s7a.ktspigot.config.formatter

import dev.s7a.ktspigot.config.KtConfigFormatter
import org.bukkit.Bukkit
import org.bukkit.Location

/**
 * デフォルトとして使用する [Location] のコンフィグフォーマッタ
 *
 * @see dev.s7a.ktspigot.config.type.locationValue
 * @since 1.0.0
 */
object DefaultLocationFormatter : KtConfigFormatter<Location> {
    override val name: String = this::class.java.simpleName

    override fun string(value: Location): String {
        return value.run {
            if (yaw == 0F && pitch == 0F) {
                "${world?.name}, $x, $y, $z"
            } else {
                "${world?.name}, $x, $y, $z, $yaw, $pitch"
            }
        }
    }

    override fun value(string: String): Location? {
        return string.split("\\s*,\\s*".toRegex()).let {
            when (val size = it.size) {
                4, 6 -> {
                    val world = Bukkit.getWorld(it[0])
                    val x = it[1].toDoubleOrNull() ?: return null
                    val y = it[2].toDoubleOrNull() ?: return null
                    val z = it[3].toDoubleOrNull() ?: return null
                    if (size == 6) {
                        val yaw = it[4].toFloatOrNull() ?: return null
                        val pitch = it[5].toFloatOrNull() ?: return null
                        Location(world, x, y, z, yaw, pitch)
                    } else {
                        Location(world, x, y, z)
                    }
                }
                else -> {
                    null
                }
            }
        }
    }
}
