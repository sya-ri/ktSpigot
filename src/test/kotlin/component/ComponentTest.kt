package component

import com.google.gson.Gson
import com.google.gson.JsonParser
import dev.s7a.ktspigot.component.appendKeybind
import dev.s7a.ktspigot.component.appendScore
import dev.s7a.ktspigot.component.appendSelector
import dev.s7a.ktspigot.component.appendTranslatable
import dev.s7a.ktspigot.component.buildComponent
import dev.s7a.ktspigot.component.clickCopyToClipboard
import dev.s7a.ktspigot.component.clickOpenUrl
import dev.s7a.ktspigot.component.clickRunCommand
import dev.s7a.ktspigot.component.clickSuggestCommand
import dev.s7a.ktspigot.component.hoverShowText
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.Keybinds
import randomString
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * コンポーネントに関するテスト
 *
 * @see dev.s7a.ktspigot.component
 */
class ComponentTest {
    @Test
    fun `component can be get`() {
        assertEquals(
            """
                [
                   {
                      "extra":[
                         {
                            "hoverEvent":{
                               "action":"show_text",
                               "value":[
                                  {
                                     "text":"a"
                                  }
                               ]
                            },
                            "text":"1"
                         },
                         {
                            "clickEvent":{
                               "action":"run_command",
                               "value":"b"
                            },
                            "text":"2"
                         },
                         {
                            "clickEvent":{
                               "action":"run_command",
                               "value":"c"
                            },
                            "text":"3"
                         },
                         {
                            "clickEvent":{
                               "action":"suggest_command",
                               "value":"d"
                            },
                            "text":"4"
                         },
                         {
                            "clickEvent":{
                               "action":"copy_to_clipboard",
                               "value":"e"
                            },
                            "text":"5"
                         }
                      ],
                      "text":""
                   }
                ]
            """.trimIndent().let {
                Gson().toJson(JsonParser().parse(it))
            },
            buildComponent {
                append("1", hoverEvent = hoverShowText("a"))
                append("2", clickEvent = clickOpenUrl("b"))
                append("3", clickEvent = clickRunCommand("c"))
                append("4", clickEvent = clickSuggestCommand("d"))
                append("5", clickEvent = clickCopyToClipboard("e"))
            }.toJson()
        )
    }

    @Test
    fun `colored component can be get`() {
        val text = randomString()
        assertEquals(
            """
                [{"extra":[{"color":"dark_gray","text":"$text"}],"text":""}]
            """.trimIndent(),
            buildComponent {
                append(text, ChatColor.DARK_GRAY)
            }.toJson()
        )
        assertEquals(
            """
                [{"extra":[{"color":"dark_gray","text":"$text"}],"text":""}]
            """.trimIndent(),
            buildComponent {
                append("&8$text")
            }.toJson()
        )
    }

    @Test
    fun `styled component can be get`() {
        val text = randomString()
        val bold = Random.nextBoolean()
        val italic = Random.nextBoolean()
        val underlined = Random.nextBoolean()
        val strikethrough = Random.nextBoolean()
        val obfuscated = Random.nextBoolean()
        assertEquals(
            """
                [{"extra":[{"bold":$bold,"italic":$italic,"underlined":$underlined,"strikethrough":$strikethrough,"obfuscated":$obfuscated,"color":"aqua","text":"$text"}],"text":""}]
            """.trimIndent(),
            buildComponent {
                append(text, ChatColor.AQUA, bold, italic, underlined, strikethrough, obfuscated)
            }.toJson()
        )
    }

    @Test
    fun `translatable component can be get`() {
        assertEquals(
            """
                [{"extra":[{"translate":"language.name"}],"text":""}]
            """.trimIndent(),
            buildComponent {
                appendTranslatable("language.name")
            }.toJson()
        )
    }

    @Test
    fun `keybind component can be get`() {
        assertEquals(
            """
                [{"extra":[{"keybind":"key.chat"}],"text":""}]
            """.trimIndent(),
            buildComponent {
                appendKeybind(Keybinds.CHAT)
            }.toJson()
        )
    }

    @Test
    fun `score component can be get`() {
        assertEquals(
            """
                [{"extra":[{"score":{"name":"@p","objective":"test"}}],"text":""}]
            """.trimIndent(),
            buildComponent {
                appendScore("@p", "test")
            }.toJson()
        )
    }

    @Test
    fun `selector component can be get`() {
        assertEquals(
            """
                [{"extra":[{"selector":"@p"}],"text":""}]
            """.trimIndent(),
            buildComponent {
                appendSelector("@p")
            }.toJson()
        )
    }
}
