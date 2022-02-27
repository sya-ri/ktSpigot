package dev.s7a.ktspigot.player

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.UUID

/**
 * 仮想プレイヤー
 *
 * @property uniqueId [UUID]
 * @since 1.0.0
 */
data class VirtualPlayer(val uniqueId: UUID) {
    /**
     * プレイヤーとして取得する
     *
     * @since 1.0.0
     */
    val player: Player?
        get() = Bukkit.getPlayer(uniqueId)

    /**
     * オフラインプレイヤーとして取得する
     *
     * @since 1.0.0
     */
    val offlinePlayer: OfflinePlayer?
        get() = Bukkit.getOfflinePlayer(uniqueId)

    override fun toString(): String {
        return uniqueId.toString()
    }

    companion object {
        /**
         * プレイヤーを仮想化する
         *
         * @since 1.0.0
         */
        fun OfflinePlayer.toVirtual() = VirtualPlayer(uniqueId)
    }
}
