package dev.s7a.spigot

import org.bukkit.Location
import org.bukkit.OfflinePlayer
import java.util.UUID

class TestPlayerList {
    private val onlinePlayers = mutableSetOf<TestPlayer>()

    fun addPlayer(uniqueId: UUID, name: String, location: Location) = TestPlayer(uniqueId, name, location).apply(onlinePlayers::add)

    fun getPlayer(id: UUID): TestPlayer? {
        return onlinePlayers.firstOrNull { it.uniqueId == id }
    }

    fun getOfflinePlayer(id: UUID): OfflinePlayer {
        return getPlayer(id) ?: TestOfflinePlayer(id)
    }
}
