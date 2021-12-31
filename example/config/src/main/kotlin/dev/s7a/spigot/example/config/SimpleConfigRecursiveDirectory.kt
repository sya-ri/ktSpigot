package dev.s7a.spigot.example.config

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigDirectory
import dev.s7a.spigot.example.config.Main.Companion.plugin
import java.io.File

/**
 * [KtConfigDirectory.Recursive] の例
 */
object SimpleConfigRecursiveDirectory : KtConfigDirectory.Recursive<SimpleConfigRecursiveDirectory.ConfigFile>(plugin, "simple-recursive") {
    class ConfigFile(file: File) : KtConfig(file)

    override fun load(file: File) = ConfigFile(file)
}
