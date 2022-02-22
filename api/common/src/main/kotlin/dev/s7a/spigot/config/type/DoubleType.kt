package dev.s7a.spigot.config.type

/**
 * [Double] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.doubleValue
 * @since 1.0.0
 */
object DoubleType : NumberType.Base<Double>() {
    override val numberToValue = Number::toDouble
}
