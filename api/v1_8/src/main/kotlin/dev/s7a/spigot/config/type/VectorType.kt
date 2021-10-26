package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfigFormatter
import org.bukkit.util.Vector

/**
 * [Vector] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.vectorValue
 * @since 1.0.0
 */
class VectorType(formatter: KtConfigFormatter<Vector>) : FormatterType<Vector>(formatter)
