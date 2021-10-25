package dev.s7a.spigot.config.type

/**
 * [Int] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.intValue
 * @since 1.0.0
 */
object IntType : NumberType.Base<Int>() {
    override val numberToValue = Number::toInt
}
