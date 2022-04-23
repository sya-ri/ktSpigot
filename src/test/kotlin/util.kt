import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector
import kotlin.random.Random

/**
 * [randomString] に使う文字列
 */
private val strings = ('a'..'z') + ('A'..'Z')

/**
 * [randomChatColor] に使う色
 */
private val chatColors = ChatColor.values().filter { it.isColor }

/**
 * [randomFormatChatColor] に使う色
 */
private val formatChatColors = ChatColor.values().filter { it.isFormat }

/**
 * ランダムな [ChatColor] を取得する
 */
fun randomChatColor() = chatColors.random()

/**
 * ランダムな [ChatColor] を取得する
 */
fun randomFormatChatColor() = formatChatColors.random()

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
