package dev.s7a.spigot.time

/**
 * 時間の単位
 *
 * @property ticks tick に変換した時の値
 * @since 1.0.0
 */
enum class KtTimeUnit(val ticks: Long) {
    Tick(1),
    Second(20 * Tick.ticks),
    Minute(60 * Second.ticks),
    Hour(60 * Minute.ticks),
    Day(24 * Hour.ticks),
}
