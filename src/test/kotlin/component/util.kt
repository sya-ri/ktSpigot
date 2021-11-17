package component

import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.chat.ComponentSerializer

/**
 * [Array]<[BaseComponent]> を json の形式に変換する
 */
fun Array<BaseComponent>.toJson(): String = ComponentSerializer.toString(this)
