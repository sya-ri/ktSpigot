package showcase

import dev.s7a.ktspigot.config.KtConfigBase
import dev.s7a.ktspigot.config.KtConfigDataClassConverter
import dev.s7a.ktspigot.config.KtConfigError
import dev.s7a.ktspigot.config.KtConfigFormatter
import dev.s7a.ktspigot.config.KtConfigResult
import dev.s7a.ktspigot.config.setUnsafe
import dev.s7a.ktspigot.config.type.intValue
import dev.s7a.ktspigot.config.type.locationValue
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
        override fun get(config: KtConfigBase, path: String): KtConfigResult<ExampleDataClass> {
            return config.intValue("$path.int").get().map { int ->
                config.locationValue("$path.location", formatter).get().map { location ->
                    KtConfigResult.Success(ExampleDataClass(int, location))
                }
            }
        }

        override fun set(config: KtConfigBase, path: String, value: ExampleDataClass?) {
            if (value != null) {
                config.intValue("$path.int").set(value.int)
                config.locationValue("$path.location", formatter).set(value.location)
            } else {
                config.setUnsafe(path, null)
            }
        }

        override fun toValue(config: KtConfigBase, path: String, index: Int, map: Map<String, Any>): KtConfigResult<ExampleDataClass> {
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
                "location" to formatter.string(value.location)
            )
        }
    }
}
// CODE-SNIPPET END
