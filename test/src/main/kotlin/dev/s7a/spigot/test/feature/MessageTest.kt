package dev.s7a.spigot.test.feature

import dev.s7a.spigot.component.appendKeybind
import dev.s7a.spigot.component.appendTranslatable
import dev.s7a.spigot.component.hoverShowText
import dev.s7a.spigot.test.util.FeatureTest
import dev.s7a.spigot.test.util.featureTest
import dev.s7a.spigot.util.sendActionBarMessage
import dev.s7a.spigot.util.sendChatMessage
import dev.s7a.spigot.util.sendTitleMessage
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.Keybinds
import org.bukkit.entity.Player

/**
 * メッセージに関するテスト
 */
object MessageTest : FeatureTest.UseContainer(
    featureTest("action") { (sender, _, args) ->
        if (sender !is Player) return@featureTest
        if (args.isEmpty()) {
            return@featureTest sender.sendChatMessage {
                append("&c/test Component action ")
                append("&c<message>", hoverEvent = hoverShowText("&6送信するメッセージ"))
                append(" ")
                append("&c[altColorChar]", hoverEvent = hoverShowText("&6カラーコードに使う文字 / null"))
            }
        }
        val message = args[0].replace('_', ' ')
        val altColorChar = args.getOrNull(1)?.firstOrNull()
        sender.sendActionBarMessage(message, altColorChar)
    },
    featureTest("action-component") { (sender) ->
        if (sender !is Player) return@featureTest
        sender.sendActionBarMessage {
            appendTranslatable("key.inventory", color = ChatColor.GOLD)
            append("&7: ")
            appendKeybind(Keybinds.INVENTORY, color = ChatColor.GREEN, bold = true)
        }
    },
    featureTest("title") { (sender, _, args) ->
        if (sender !is Player) return@featureTest
        if (args.isEmpty()) {
            return@featureTest sender.sendChatMessage {
                append("&c/test Component action ")
                append("&c<title>", hoverEvent = hoverShowText("&6メインタイトル"))
                append(" ")
                append("&c[subtitle]", hoverEvent = hoverShowText("&6サブタイトル / ''"))
                append(" ")
                append("&c[fadeIn]", hoverEvent = hoverShowText("&6フェードイン時間 / 10"))
                append(" ")
                append("&c[stay]", hoverEvent = hoverShowText("&6表示時間 / 70"))
                append(" ")
                append("&c[fadeOut]", hoverEvent = hoverShowText("&6フェードアウト時間 / 20"))
                append(" ")
                append("&c[altColorChar]", hoverEvent = hoverShowText("&6カラーコードに使う文字"))
            }
        }
        val title = args[0].replace('_', ' ')
        val subtitle = args.getOrNull(1)?.replace('_', ' ')
        val fadeIn = args.getOrNull(2)?.toIntOrNull() ?: 10
        val stay = args.getOrNull(3)?.toIntOrNull() ?: 70
        val fadeOut = args.getOrNull(4)?.toIntOrNull() ?: 1020
        val altColorChar = args.getOrNull(5)?.firstOrNull()
        sender.sendTitleMessage(title, subtitle, fadeIn, stay, fadeOut, altColorChar)
    },
)
