package dev.s7a.spigot

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.WorldCreator
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.util.UUID
import kotlin.io.path.createTempDirectory
import kotlin.io.path.createTempFile

object KtSpigotTest {
    val server = TestServer().apply { Bukkit.setServer(this) }

    val plugin = TestPlugin(
        @Suppress("DEPRECATION") JavaPluginLoader(server),
        PluginDescriptionFile("MockPlugin", "1.0.0", "dev.s7a.spigot.MockPlugin"),
        createTempDirectory().toFile(),
        createTempFile().toFile()
    ).apply {
        onLoad()
        onEnable()
        setEnabled(true)
    }

    fun addPlayer(
        uniqueId: UUID = UUID.randomUUID(),
        name: String = randomPlayerName(),
        location: Location = Location(null, 0.0, 0.0, 0.0)
    ) = server.addPlayer(uniqueId, name, location)

    fun addWorld(name: String) = server.createWorld(WorldCreator(name))

    fun setCommand(name: String, owner: JavaPlugin) {
        server.setCommand(name, owner)
    }

    fun init() {
    }
}
