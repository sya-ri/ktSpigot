package dev.s7a.spigot.time

/**
 * 値を [KtTimeUnit.Tick] として [KtTime] に変換する
 *
 * @since 1.0.0
 */
val Int.tick
    get() = toLong().tick

/**
 * 値を [KtTimeUnit.Tick] として [KtTime] に変換する
 *
 * @since 1.0.0
 */
val Long.tick
    get() = KtTime(this, KtTimeUnit.Tick)

/**
 * 値を [KtTimeUnit.Second] として [KtTime] に変換する
 *
 * @since 1.0.0
 */
val Int.sec
    get() = toLong().sec

/**
 * 値を [KtTimeUnit.Second] として [KtTime] に変換する
 *
 * @since 1.0.0
 */
val Long.sec
    get() = KtTime(this, KtTimeUnit.Second)

/**
 * 値を [KtTimeUnit.Minute] として [KtTime] に変換する
 *
 * @since 1.0.0
 */
val Int.min
    get() = toLong().min

/**
 * 値を [KtTimeUnit.Minute] として [KtTime] に変換する
 *
 * @since 1.0.0
 */
val Long.min
    get() = KtTime(this, KtTimeUnit.Minute)

/**
 * 値を [KtTimeUnit.Hour] として [KtTime] に変換する
 *
 * @since 1.0.0
 */
val Int.hour
    get() = toLong().hour

/**
 * 値を [KtTimeUnit.Hour] として [KtTime] に変換する
 *
 * @since 1.0.0
 */
val Long.hour
    get() = KtTime(this, KtTimeUnit.Hour)

/**
 * 値を [KtTimeUnit.Day] として [KtTime] に変換する
 *
 * @since 1.0.0
 */
val Int.day
    get() = toLong().day

/**
 * 値を [KtTimeUnit.Day] として [KtTime] に変換する
 *
 * @since 1.0.0
 */
val Long.day
    get() = KtTime(this, KtTimeUnit.Day)
