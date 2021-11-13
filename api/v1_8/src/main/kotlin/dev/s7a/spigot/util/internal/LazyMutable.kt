package dev.s7a.spigot.util.internal

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 変更可能な [lazy]
 *
 * @property initializer 初期化処理
 * @since 1.0.0
 */
internal class LazyMutable<T : Any>(private val initializer: () -> T) : ReadWriteProperty<Any?, T> {
    private val lazyValue by lazy { initializer() }
    private var newValue: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return newValue ?: lazyValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        newValue = value
    }
}
