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
    private var server: TestServer? = null
    private var plugin: TestPlugin? = null

    private fun setSeverToNull() {
        try {
            val server = Bukkit::class.java.getDeclaredField("server")
            server.isAccessible = true
            server.set(null, null)
        } catch (ex: NoSuchFieldException) {
            throw RuntimeException(ex)
        } catch (ex: IllegalArgumentException) {
            throw RuntimeException(ex)
        } catch (ex: IllegalAccessException) {
            throw RuntimeException(ex)
        }
    }

    fun mock() {
        if (server != null) throw IllegalStateException()
        server = TestServer().apply { Bukkit.setServer(this) }
    }

    fun unmock() {
        server?.let {
            it.pluginManager.disablePlugins()
            plugin = null
            setSeverToNull()
            server = null
        }
    }

    fun getPlugin(): TestPlugin {
        return server?.let {
            plugin ?: TestPlugin(
                @Suppress("DEPRECATION") JavaPluginLoader(it),
                PluginDescriptionFile("TestPlugin", "1.0.0", "dev.s7a.spigot.TestPlugin"),
                createTempDirectory().toFile(),
                createTempFile().toFile()
            ).apply {
                onLoad()
                onEnable()
                setEnabled(true)
                plugin = this
            }
        } ?: throw IllegalStateException("Not mocking")
    }

    fun addPlayer(
        uniqueId: UUID = UUID.randomUUID(),
        name: String = randomPlayerName(),
        location: Location = Location(null, 0.0, 0.0, 0.0)
    ) = server!!.addPlayer(uniqueId, name, location)

    fun addWorld(name: String) = server!!.createWorld(WorldCreator(name))

    fun setCommand(name: String, owner: JavaPlugin) {
        server!!.setCommand(name, owner)
    }
}
