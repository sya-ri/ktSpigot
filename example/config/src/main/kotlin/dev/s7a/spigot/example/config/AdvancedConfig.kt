package dev.s7a.spigot.example.config

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.checkValues
import dev.s7a.spigot.config.enumNameValue
import dev.s7a.spigot.config.enumOrdinalValue
import dev.s7a.spigot.config.locationValue
import dev.s7a.spigot.config.materialValue
import dev.s7a.spigot.config.printError
import dev.s7a.spigot.config.uuidValue
import dev.s7a.spigot.config.vectorValue
import dev.s7a.spigot.example.config.Main.Companion.plugin
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.util.Vector
import java.util.UUID

/**
 * [KtConfig] の例
 */
object AdvancedConfig : KtConfig(plugin, "advanced.yml") {
    /**
     * 例として使う列挙型
     */
    enum class ExampleEnum {
        One,
        Two,
        Three,
    }

    /**
     * [Enum] を [Enum.name] としてコンフィグから取得する
     */
    private val enum1 = enumNameValue<ExampleEnum>("enum1").default(ExampleEnum.values()::random)

    /**
     * [Enum] を [Enum.name] としてコンフィグから取得する
     */
    private val enum1IgnoreCase = enumNameValue<ExampleEnum>("enum1", true).default(ExampleEnum.values()::random)

    /**
     * [Enum] を [Enum.ordinal] としてコンフィグから取得する
     */
    private val enum2 = enumOrdinalValue<ExampleEnum>("enum2").default(ExampleEnum.values()::random)

    /**
     * [Material] としてコンフィグから取得する
     */
    private val material = materialValue("material").default(Material.values()::random)

    /**
     * [org.bukkit.Location] としてコンフィグから取得する
     */
    private val location = locationValue("location").default { Bukkit.getWorlds().first().spawnLocation }

    /**
     * [UUID] としてコンフィグから取得する
     */
    private val uuid = uuidValue("uuid").default(UUID::randomUUID)

    /**
     * [Vector] としてコンフィグから取得する
     */
    private val vector = vectorValue("vector").default(::Vector)

    override fun load() {
        checkValues().printError(plugin.logger)
        println("enum1: ${enum1.get()} / ${enum1.getValue()}")
        println("enum1 (ignoreCase): ${enum1IgnoreCase.get()} / ${enum1IgnoreCase.getValue()}")
        println("enum2: ${enum2.get()} / ${enum2.getValue()}")
        println("material: ${material.get()} / ${material.getValue()}")
        println("location: ${location.get()} / ${location.getValue()}")
        println("uuid: ${uuid.get()} / ${uuid.getValue()}")
        println("vector: ${vector.get()} / ${vector.getValue()}")
    }
}
