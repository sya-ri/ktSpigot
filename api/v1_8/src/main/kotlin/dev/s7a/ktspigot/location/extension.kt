package dev.s7a.ktspigot.location

import org.bukkit.Location
import org.bukkit.World

/**
 * ```
 * val (world) = location
 * ```
 *
 * @see Location.getWorld
 * @since 1.0.0
 */
operator fun Location.component1(): World? = world

/**
 * ```
 * val (_, x) = location
 * ```
 *
 * @see Location.getX
 * @since 1.0.0
 */
operator fun Location.component2() = x

/**
 * ```
 * val (_, _, y) = location
 * ```
 *
 * @see Location.getY
 * @since 1.0.0
 */
operator fun Location.component3() = y

/**
 * ```
 * val (_, _, _, z) = location
 * ```
 *
 * @see Location.getZ
 * @since 1.0.0
 */
operator fun Location.component4() = z

/**
 * ```
 * val (_, _, _, _, yaw) = location
 * ```
 *
 * @see Location.getYaw
 * @since 1.0.0
 */
operator fun Location.component5() = yaw

/**
 * ```
 * val (_, _, _, _, _, pitch) = location
 * ```
 *
 * @see Location.getPitch
 * @since 1.0.0
 */
operator fun Location.component6() = pitch
