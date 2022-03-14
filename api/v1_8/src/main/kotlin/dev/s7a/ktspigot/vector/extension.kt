package dev.s7a.ktspigot.vector

import org.bukkit.util.Vector

/**
 * ```
 * val (x) = vector
 * ```
 *
 * @see Vector.getX
 * @since 1.0.0
 */
operator fun Vector.component1() = x

/**
 * ```
 * val (_, y) = vector
 * ```
 *
 * @see Vector.getY
 * @since 1.0.0
 */
operator fun Vector.component2() = y

/**
 * ```
 * val (_, _, z) = vector
 * ```
 *
 * @see Vector.getZ
 * @since 1.0.0
 */
operator fun Vector.component3() = z
