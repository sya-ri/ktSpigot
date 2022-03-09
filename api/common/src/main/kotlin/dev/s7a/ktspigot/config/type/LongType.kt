package dev.s7a.ktspigot.config.type

/**
 * [Long] のコンフィグデータ型
 *
 * @see longValue
 * @since 1.0.0
 */
object LongType : NumberType.Base<Long>() {
    override val numberToValue = Number::toLong
}
