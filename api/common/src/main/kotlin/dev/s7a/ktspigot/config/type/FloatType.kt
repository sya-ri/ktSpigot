package dev.s7a.ktspigot.config.type

/**
 * [Float] のコンフィグデータ型
 *
 * @see dev.s7a.ktspigot.config.floatValue
 * @since 1.0.0
 */
object FloatType : NumberType.Base<Float>() {
    override val numberToValue = Number::toFloat
}
