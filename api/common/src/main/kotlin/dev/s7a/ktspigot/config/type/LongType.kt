package dev.s7a.ktspigot.config.type

/**
 * [Long] のコンフィグデータ型
 *
 * @see dev.s7a.ktspigot.config.longValue
 * @since 1.0.0
 */
object LongType : NumberType.Base<Long>() {
    override val numberToValue = Number::toLong
}
