import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector
import kotlin.random.Random

/**
 * [randomString] に使う文字列
 */
private val strings = ('a'..'z') + ('A'..'Z')

/**
 * ランダムな文字列を取得する
 */
fun randomString(length: Int = 16) = List(length) { strings.random() }.joinToString("")

/**
 * ランダムな座標を取得する
 */
fun randomLocation(world: World?): Location {
    return Location(world, Random.nextDouble(), Random.nextDouble(), Random.nextDouble())
}

/**
 * ランダムなベクトルを取得する
 */
fun randomVector(): Vector {
    return Vector(Random.nextDouble(), Random.nextDouble(), Random.nextDouble())
}
