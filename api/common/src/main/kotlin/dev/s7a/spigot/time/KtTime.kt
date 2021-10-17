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

    override fun compareTo(other: KtTime): Int {
        return ticks.compareTo(other.ticks)
    }
}
