package dev.s7a.spigot.config.type

import dev.s7a.spigot.config.KtConfigFormatter
import org.bukkit.Location

/**
 * [Location] のコンフィグデータ型
 *
 * @see dev.s7a.spigot.config.locationValue
 * @since 1.0.0
 */
class LocationType(formatter: KtConfigFormatter<Location>) : FormatterType<Location>(formatter)
