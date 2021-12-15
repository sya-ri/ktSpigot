package dev.s7a.spigot.scheduler

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
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

/**
 * [BukkitRunnable] を生成する
 *
 * @param action 実行する処理
 * @see BukkitRunnable
 * @since 1.0.0
 */
inline fun bukkitRunnable(crossinline action: BukkitRunnable.() -> Unit): BukkitRunnable {
    return object : BukkitRunnable() {
        override fun run() = action()
    }
}

/**
 * [BukkitRunnable.runTask] を実行する
 *
 * @param action 実行する処理
 * @return タスク
 * @see BukkitRunnable.runTask
 * @since 1.0.0
 */
inline fun JavaPlugin.runTask(crossinline action: BukkitRunnable.() -> Unit): BukkitTask {
    return bukkitRunnable(action).runTask(this)
}

/**
 * [BukkitRunnable.runTaskAsynchronously] を実行する
 *
 * @param action 実行する処理
 * @return タスク
 * @see BukkitRunnable.runTaskLaterAsynchronously
 * @since 1.0.0
 */
inline fun JavaPlugin.runTaskAsync(crossinline action: BukkitRunnable.() -> Unit): BukkitTask {
    return bukkitRunnable(action).runTaskAsynchronously(this)
}

/**
 * [BukkitRunnable.runTaskLater] を実行する
 *
 * @param delay 遅らせる時間(tick)
 * @param action 実行する処理
 * @return タスク
 * @see BukkitRunnable.runTaskLater
 * @since 1.0.0
 */
inline fun JavaPlugin.runTaskLater(delay: Long, crossinline action: BukkitRunnable.() -> Unit): BukkitTask {
    return bukkitRunnable(action).runTaskLater(this, delay)
}

/**
 * [BukkitRunnable.runTaskLater] を実行する
 *
 * @param delay 遅らせる時間
 * @param action 実行する処理
 * @return タスク
 * @see BukkitRunnable.runTaskLater
 * @since 1.0.0
 */
inline fun JavaPlugin.runTaskLater(delay: Duration, crossinline action: BukkitRunnable.() -> Unit): BukkitTask {
    return runTaskLater(delay.asTicks, action)
}

/**
 * [BukkitRunnable.runTaskLaterAsynchronously] を実行する
 *
 * @param delay 遅らせる時間(tick)
 * @param action 実行する処理
 * @return タスク
 * @see BukkitRunnable.runTaskLaterAsynchronously
 * @since 1.0.0
 */
inline fun JavaPlugin.runTaskLaterAsync(delay: Long, crossinline action: BukkitRunnable.() -> Unit): BukkitTask {
    return bukkitRunnable(action).runTaskLaterAsynchronously(this, delay)
}

/**
 * [BukkitRunnable.runTaskLaterAsynchronously] を実行する
 *
 * @param delay 遅らせる時間
 * @param action 実行する処理
 * @return タスク
 * @see BukkitRunnable.runTaskLaterAsynchronously
 * @since 1.0.0
 */
inline fun JavaPlugin.runTaskLaterAsync(delay: Duration, crossinline action: BukkitRunnable.() -> Unit): BukkitTask {
    return runTaskLaterAsync(delay.asTicks, action)
}

/**
 * [BukkitRunnable.runTaskTimer] を実行する
 *
 * @param period 繰り返す間隔(tick)
 * @param delay 遅らせる時間(tick)
 * @param action 実行する処理
 * @return タスク
 * @see BukkitRunnable.runTaskTimer
 * @since 1.0.0
 */
inline fun JavaPlugin.runTaskTimer(period: Long, delay: Long = 0L, crossinline action: BukkitRunnable.() -> Unit): BukkitTask {
    return bukkitRunnable(action).runTaskTimer(this, delay, period)
}

/**
 * [BukkitRunnable.runTaskTimer] を実行する
 *
 * @param period 繰り返す間隔
 * @param delay 遅らせる時間
 * @param action 実行する処理
 * @return タスク
 * @see BukkitRunnable.runTaskTimer
 * @since 1.0.0
 */
inline fun JavaPlugin.runTaskTimer(period: Duration, delay: Duration = Duration.ZERO, crossinline action: BukkitRunnable.() -> Unit): BukkitTask {
    return runTaskTimer(period.asTicks, delay.asTicks, action)
}

/**
 * [BukkitRunnable.runTaskTimerAsynchronously] を実行する
 *
 * @param period 繰り返す間隔(tick)
 * @param delay 遅らせる時間(tick)
 * @param action 実行する処理
 * @return タスク
 * @see BukkitRunnable.runTaskTimerAsynchronously
 * @since 1.0.0
 */
inline fun JavaPlugin.runTaskTimerAsync(period: Long, delay: Long = 0L, crossinline action: BukkitRunnable.() -> Unit): BukkitTask {
    return bukkitRunnable(action).runTaskTimerAsynchronously(this, delay, period)
}

/**
 * [BukkitRunnable.runTaskTimerAsynchronously] を実行する
 *
 * @param period 繰り返す間隔
 * @param delay 遅らせる時間
 * @param action 実行する処理
 * @return タスク
 * @see BukkitRunnable.runTaskTimerAsynchronously
 * @since 1.0.0
 */
inline fun JavaPlugin.runTaskTimerAsync(period: Duration, delay: Duration = Duration.ZERO, crossinline action: BukkitRunnable.() -> Unit): BukkitTask {
    return runTaskTimerAsync(period.asTicks, delay.asTicks, action)
}
