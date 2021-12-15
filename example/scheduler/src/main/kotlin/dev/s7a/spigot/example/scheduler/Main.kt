package dev.s7a.spigot.example.scheduler

import dev.s7a.spigot.scheduler.runTaskLater
import dev.s7a.spigot.scheduler.runTaskTimerAsync
import dev.s7a.spigot.scheduler.ticks
import org.bukkit.plugin.java.JavaPlugin
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * メインクラス
 */
@Suppress("unused")
class Main : JavaPlugin() {
    override fun onEnable() {
        runTaskLater(1.minutes - 50.seconds) { // 10 秒後に実行
            logger.info("10 sec later")
        }
        runTaskLater(1.minutes + 30.seconds) { // 1.5 分後に実行
            logger.info("1.5 min later")
        }
        runTaskTimerAsync(3.minutes, 10.ticks) { // 10 tick 後から 3 分毎に繰り返し実行
            logger.info("3 min timer")
        }
    }
}
