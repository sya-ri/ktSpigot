package dev.s7a.spigot.example.scheduler

import dev.s7a.spigot.scheduler.runTaskLater
import dev.s7a.spigot.scheduler.runTaskTimerAsync
import dev.s7a.spigot.time.min
import dev.s7a.spigot.time.sec
import dev.s7a.spigot.time.tick
import org.bukkit.plugin.java.JavaPlugin

/**
 * メインクラス
 */
@Suppress("unused")
class Main : JavaPlugin() {
    override fun onEnable() {
        runTaskLater(30.sec) { // 30 秒後に実行
            logger.info("30 sec later")
        }
        runTaskTimerAsync(3.min, 10.tick) { // 10 tick 後から 3 分毎に繰り返し実行
            logger.info("3 min timer")
        }
    }
}
