package dev.s7a.spigot.scheduler

import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.DurationUnit

/**
 * [Duration] を tick に変換する
 *
 * @since 1.0.0
 */
inline val Duration.asTicks
    get() = toLong(DurationUnit.SECONDS) * 20

/**
 * tick として [Duration] を定義する
 *
 * @since 1.0.0
 */
inline val Int.ticks
    get() = (this * 50).milliseconds

/**
 * tick として [Duration] を定義する
 *
 * @since 1.0.0
 */
inline val Long.ticks
    get() = (this * 50).milliseconds
