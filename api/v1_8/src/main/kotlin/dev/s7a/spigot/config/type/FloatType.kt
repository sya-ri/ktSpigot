package dev.s7a.spigot.config.type

/**
 * [Float] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.floatValue
 * @since 1.0.0
 */
object FloatType : NumberType.Base<Float>() {
    override val numberToValue = Number::toFloat
}
