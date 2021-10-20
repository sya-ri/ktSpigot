package dev.s7a.spigot.example.config

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigDirectory
import dev.s7a.spigot.example.config.Main.Companion.plugin
import java.io.File

/**
 * [KtConfigDirectory] の例
 */
object SimpleConfigDirectory : KtConfigDirectory<SimpleConfigDirectory.ConfigFile>(plugin, "simple") {
    class ConfigFile(file: File) : KtConfig(file)

    override fun new(file: File) = ConfigFile(file)
}
