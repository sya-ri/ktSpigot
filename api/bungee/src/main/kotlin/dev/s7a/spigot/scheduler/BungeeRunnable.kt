package dev.s7a.spigot.scheduler

import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.api.scheduler.ScheduledTask
import java.util.concurrent.TimeUnit

/**
 * This class is provided as an easy way to handle scheduling tasks.
 *
 * @since 1.0.0
 */
abstract class BungeeRunnable : Runnable {
    /**
     * Gets the task id for this runnable.
     *
     * @return the task id that this runnable was scheduled as
     * @throws IllegalStateException if task was not scheduled yet
     * @since 1.0.0
     */
    var taskId = -1
        @Synchronized
        @Throws(IllegalStateException::class)
        get() {
            check(field != -1) { "Not scheduled yet" }
            return field
        }
        private set

    /**
     * Attempts to cancel this task.
     *
     * @throws IllegalStateException if task was not scheduled yet
     * @since 1.0.0
     */
    @Synchronized
    @Throws(IllegalStateException::class)
    fun cancel() {
        ProxyServer.getInstance().scheduler.cancel(taskId)
    }

    /**
     * Schedule a task to be executed asynchronously.
     *
     * @param plugin the reference to the plugin scheduling task
     * @return a ScheduledTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException if this was already scheduled
     * @since 1.0.0
     */
    @Synchronized
    @Throws(IllegalArgumentException::class, IllegalStateException::class)
    fun runTaskAsynchronously(plugin: Plugin): ScheduledTask {
        checkState()
        return setupId(ProxyServer.getInstance().scheduler.runAsync(plugin, this))
    }

    /**
     * Schedules this to run asynchronously after the specified number of
     * server ticks.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param delay the ticks to wait before running the task
     * @return a ScheduledTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException if this was already scheduled
     * @since 1.0.0
     */
    @Synchronized
    @Throws(IllegalArgumentException::class, IllegalStateException::class)
    fun runTaskLaterAsynchronously(plugin: Plugin, delay: Long): ScheduledTask {
        checkState()
        return setupId(ProxyServer.getInstance().scheduler.schedule(plugin, this, delay, TimeUnit.MICROSECONDS))
    }

    /**
     * Schedules this to repeatedly run asynchronously until cancelled,
     * starting after the specified number of server ticks.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param delay the ticks to wait before running the task for the first
     * time
     * @param period the ticks to wait between runs
     * @return a ScheduledTask that contains the id number
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException if this was already scheduled
     * @since 1.0.0
     */
    @Synchronized
    @Throws(IllegalArgumentException::class, IllegalStateException::class)
    fun runTaskTimerAsynchronously(
        plugin: Plugin,
        delay: Long,
        period: Long
    ): ScheduledTask {
        checkState()
        return setupId(
            ProxyServer.getInstance().scheduler.schedule(plugin, this, delay, period, TimeUnit.MICROSECONDS)
        )
    }

    private fun checkState() {
        check(taskId == -1) { "Already scheduled as $taskId" }
    }

    private fun setupId(task: ScheduledTask): ScheduledTask {
        taskId = task.id
        return task
    }
}
