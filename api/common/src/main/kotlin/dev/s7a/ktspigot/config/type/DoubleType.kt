package dev.s7a.ktspigot.config.type

/**
 * [Double] のコンフィグデータ型
 *
 * @see dev.s7a.ktspigot.config.doubleValue
 * @since 1.0.0
 */
object DoubleType : NumberType.Base<Double>() {
    override val numberToValue = Number::toDouble
}
