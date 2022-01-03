package showcase

import dev.s7a.spigot.config.KtConfig
import dev.s7a.spigot.config.KtConfigDataClassConverter
import dev.s7a.spigot.config.KtConfigError
import dev.s7a.spigot.config.KtConfigFormatter
import dev.s7a.spigot.config.KtConfigResult
import dev.s7a.spigot.config.setUnsafe
import dev.s7a.spigot.config.type.intValue
import dev.s7a.spigot.config.type.locationValue
import org.bukkit.Location

// CODE-SNIPPET BEGIN
/**
 * 例として使うデータクラス
 */
data class ExampleDataClass(val int: Int, val location: Location) {
    /**
     * コンバータ
     */
    class Converter(private val formatter: KtConfigFormatter<Location>) : KtConfigDataClassConverter.Listable<ExampleDataClass> {
        override fun get(config: KtConfig, path: String): KtConfigResult<ExampleDataClass> {
            return config.intValue("$path.int").get().map { int ->
                config.locationValue("$path.location", formatter).get().map { location ->
                    KtConfigResult.Success(ExampleDataClass(int, location))
                }
            }
        }

        override fun set(config: KtConfig, path: String, value: ExampleDataClass?) {
            if (value != null) {
                config.intValue("$path.int").set(value.int)
                config.locationValue("$path.location", formatter).set(value.location)
            } else {
                config.setUnsafe(path, null)
            }
        }

        override fun toValue(config: KtConfig, path: String, index: Int, map: Map<String, Any>): KtConfigResult<ExampleDataClass> {
            return try {
                val int = map["int"] as Int
                val locationString = map["location"] as String
                formatter.value(locationString)?.let { location ->
                    KtConfigResult.Success(ExampleDataClass(int, location))
                } ?: KtConfigResult.Failure(KtConfigError.IllegalFormat(config, "$path#$index", locationString, formatter))
            } catch (ex: ClassCastException) {
                KtConfigResult.Failure(KtConfigError.ClassCastException(config, "$path#$index", ExampleDataClass::class.java.simpleName))
            } catch (ex: NullPointerException) {
                KtConfigResult.Failure(KtConfigError.ClassCastException(config, "$path#$index", ExampleDataClass::class.java.simpleName))
            }
        }

        override fun toMap(value: ExampleDataClass): Map<String, Any> {
            return mapOf(
                "int" to value.int,
                "location" to formatter.string(value.location),
            )
        }
    }
}
// CODE-SNIPPET END
