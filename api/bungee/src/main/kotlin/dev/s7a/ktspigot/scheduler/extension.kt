@file:JvmName("ExtensionBungee")

package dev.s7a.ktspigot.scheduler

import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.api.scheduler.ScheduledTask
import kotlin.time.Duration

/**
 * [BungeeRunnable] を生成する
 *
 * @param action 実行する処理
 * @see BungeeRunnable
 * @since 1.0.0
 */
inline fun bungeeRunnable(crossinline action: BungeeRunnable.() -> Unit): BungeeRunnable {
    return object : BungeeRunnable() {
        override fun run() = action()
    }
}

/**
 * [BungeeRunnable.runTaskAsynchronously] を実行する
 *
 * @param action 実行する処理
 * @return タスク
 * @see BungeeRunnable.runTaskLaterAsynchronously
 * @since 1.0.0
 */
inline fun Plugin.runTaskAsync(crossinline action: BungeeRunnable.() -> Unit): ScheduledTask {
    return bungeeRunnable(action).runTaskAsynchronously(this)
}

/**
 * [BungeeRunnable.runTaskLaterAsynchronously] を実行する
 *
 * @param delay 遅らせる時間(tick)
 * @param action 実行する処理
 * @return タスク
 * @see BungeeRunnable.runTaskLaterAsynchronously
 * @since 1.0.0
 */
inline fun Plugin.runTaskLaterAsync(delay: Long, crossinline action: BungeeRunnable.() -> Unit): ScheduledTask {
    return bungeeRunnable(action).runTaskLaterAsynchronously(this, delay * 50)
}

/**
 * [BungeeRunnable.runTaskLaterAsynchronously] を実行する
 *
 * @param delay 遅らせる時間
 * @param action 実行する処理
 * @return タスク
 * @see BungeeRunnable.runTaskLaterAsynchronously
 * @since 1.0.0
 */
inline fun Plugin.runTaskLaterAsync(delay: Duration, crossinline action: BungeeRunnable.() -> Unit): ScheduledTask {
    return runTaskLaterAsync(delay.asTicks, action)
}

/**
 * [BungeeRunnable.runTaskTimerAsynchronously] を実行する
 *
 * @param period 繰り返す間隔(tick)
 * @param delay 遅らせる時間(tick)
 * @param action 実行する処理
 * @return タスク
 * @see BungeeRunnable.runTaskTimerAsynchronously
 * @since 1.0.0
 */
inline fun Plugin.runTaskTimerAsync(period: Long, delay: Long = 0L, crossinline action: BungeeRunnable.() -> Unit): ScheduledTask {
    return bungeeRunnable(action).runTaskTimerAsynchronously(this, delay * 50, period * 50)
}

/**
 * [BungeeRunnable.runTaskTimerAsynchronously] を実行する
 *
 * @param period 繰り返す間隔
 * @param delay 遅らせる時間
 * @param action 実行する処理
 * @return タスク
 * @see BungeeRunnable.runTaskTimerAsynchronously
 * @since 1.0.0
 */
inline fun Plugin.runTaskTimerAsync(period: Duration, delay: Duration = Duration.ZERO, crossinline action: BungeeRunnable.() -> Unit): ScheduledTask {
    return runTaskTimerAsync(period.asTicks, delay.asTicks, action)
}
