package dev.s7a.ktspigot.location

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.NumberConversions.square
import org.bukkit.util.Vector
import kotlin.math.sqrt

/**
 * ブロック座標
 *
 * @property world ワールド
 * @property x X 座標
 * @property y Y 座標
 * @property z Z 座標
 * @since 1.0.0
 */
data class BlockLocation(
    var world: World?,
    var x: Int,
    var y: Int,
    var z: Int
) {
    companion object {
        /**
         * [Location] を [BlockLocation]　として取得する
         *
         * @since 1.0.0
         */
        fun Location.toBlockLocation() = BlockLocation(this)
    }

    /**
     * [Location] から [BlockLocation] のインスタンスを生成する
     *
     * @param location 座標
     * @since 1.0.0
     */
    constructor(location: Location) : this(location.world, location.blockX, location.blockY, location.blockZ)

    /**
     * ブロック
     *
     * @since 1.0.0
     */
    val block
        get() = world?.getBlockAt(x, y, z)

    /**
     * [Location] として取得する
     *
     * @since 1.0.0
     */
    fun toLocation() = Location(world, x.toDouble(), y.toDouble(), z.toDouble())

    /**
     * [Vector] として取得する
     *
     * @since 1.0.0
     */
    fun toVector() = Vector(x, y, z)

    /**
     * 別の座標の値を加算する
     *
     * @param location 別の座標
     * @since 1.0.0
     */
    fun add(location: BlockLocation) = add(location.x, location.y, location.z)

    /**
     * [x], [y], [z] を座標に加算する
     *
     * @since 1.0.0
     */
    fun add(x: Int, y: Int, z: Int) = apply {
        this.x += x
        this.y += y
        this.z += z
    }

    /**
     * 別の座標の値を減算する
     *
     * @param location 別の座標
     * @since 1.0.0
     */
    fun subtract(location: BlockLocation) = subtract(location.x, location.y, location.z)

    /**
     * [x], [y], [z] を座標に減算する
     *
     * @since 1.0.0
     */
    fun subtract(x: Int, y: Int, z: Int) = apply {
        this.x -= x
        this.y -= y
        this.z -= z
    }

    /**
     * [x], [y], [z] をそれぞれ [m] で乗算する
     *
     * @since 1.0.0
     */
    fun multiply(m: Int) = apply {
        this.x *= m
        this.y *= m
        this.z *= m
    }

    /**
     * [x], [y], [z] を 0 にする
     *
     * @since 1.0.0
     */
    fun zero() = apply {
        this.x = 0
        this.y = 0
        this.z = 0
    }

    /**
     * 位置の大きさを取得する
     *
     * @since 1.0.0
     */
    fun length() = sqrt(lengthSquared())

    /**
     * 位置の2乗の大きさを取得する
     *
     * @since 1.0.0
     */
    fun lengthSquared() = square(x.toDouble()) + square(y.toDouble()) + square(z.toDouble())

    /**
     * 距離の大きさを取得する
     *
     * @since 1.0.0
     */
    fun distance(o: BlockLocation) = sqrt(distanceSquared(o))

    /**
     * 距離の2乗の大きさを取得する
     *
     * @since 1.0.0
     */
    fun distanceSquared(o: BlockLocation): Double {
        require(o.world != null && world != null) { "Cannot measure distance to a null world" }
        require(o.world === world) { "Cannot measure distance between " + world!!.name + " and " + o.world!!.name }
        return square((x - o.x).toDouble()) + square((y - o.y).toDouble()) + square((z - o.z).toDouble())
    }
}
