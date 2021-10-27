package dev.s7a.spigot.time

/**
 * 時間
 *
 * @property time 時間
 * @property unit 単位
 * @see Int.sec
 * @see Long.sec
 * @see Int.min
 * @see Long.min
 * @see Int.hour
 * @see Long.hour
 * @see Int.day
 * @see Long.day
 * @since 1.0.0
 */
data class KtTime(val time: Long, val unit: KtTimeUnit) : Comparable<KtTime> {
    /**
     * Tick に変換する
     * @since 1.0.0
     */
    val ticks
        get() = time * unit.ticks

    override fun compareTo(other: KtTime) = ticks.compareTo(other.ticks)
    operator fun plus(other: KtTime) = KtTime(ticks + other.ticks, KtTimeUnit.Tick)
    operator fun minus(other: KtTime) = KtTime(ticks - other.ticks, KtTimeUnit.Tick)
}
