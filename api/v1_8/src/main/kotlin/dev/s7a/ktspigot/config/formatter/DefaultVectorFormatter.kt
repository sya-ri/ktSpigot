package dev.s7a.ktspigot.config.formatter

import dev.s7a.ktspigot.config.KtConfigFormatter
import org.bukkit.util.Vector

/**
 * デフォルトとして使用する [Vector] のコンフィグフォーマッタ
 *
 * @see dev.s7a.ktspigot.config.vectorValue
 * @since 1.0.0
 */
object DefaultVectorFormatter : KtConfigFormatter<Vector> {
    override val name: String = this::class.java.simpleName

    override fun string(value: Vector): String {
        return value.run {
            "$x, $y, $z"
        }
    }

    override fun value(string: String): Vector? {
        return string.split("\\s*,\\s*".toRegex()).let {
            val x = it[0].toDoubleOrNull() ?: return null
            val y = it[1].toDoubleOrNull() ?: return null
            val z = it[2].toDoubleOrNull() ?: return null
            Vector(x, y, z)
        }
    }
}
