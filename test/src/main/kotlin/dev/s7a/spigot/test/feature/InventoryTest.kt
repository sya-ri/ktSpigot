package dev.s7a.spigot.test.feature

import dev.s7a.spigot.inventory.item
import dev.s7a.spigot.inventory.ktInventory
import dev.s7a.spigot.test.Main.Companion.plugin
import dev.s7a.spigot.test.util.FeatureTest
import dev.s7a.spigot.test.util.featureTest
import dev.s7a.spigot.util.sendChatMessage
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * インベントリに関するテスト
 */
object InventoryTest : FeatureTest.UseContainer(
    featureTest("with-log") { (sender) ->
        if (sender !is Player) return@featureTest
        plugin.ktInventory("with-log1", 1) {
            sender.sendChatMessage("&2init1")
            onClick { sender.sendChatMessage("&2onClick1") }
            onClickResult { (_, isInvoked) -> sender.sendChatMessage("&2onClickResult1($isInvoked)") }
            onClose { sender.sendChatMessage("&2onClose1") }
            item(0, Material.STONE, displayName = "&3ktInventory") {
                sender.sendChatMessage("&2click1[0]")
                plugin.ktInventory("with-log2", 1) {
                    sender.sendChatMessage("&3init2")
                    onClick { sender.sendChatMessage("&3onClick2") }
                    onClickResult { (_, isInvoked) -> sender.sendChatMessage("&3onClickResult2($isInvoked)") }
                    onClose { sender.sendChatMessage("&3onClose2") }
                    item(0, Material.STONE) {
                        sender.sendChatMessage("&3click2")
                    }
                }.open(sender)
                sender.sendChatMessage("&3open2")
            }
            item(1, Material.STONE, displayName = "&3Bukkit#createInventory") {
                sender.sendChatMessage("&2click1[1]")
                sender.openInventory(Bukkit.createInventory(null, 9))
            }
        }.open(sender)
        sender.sendChatMessage("&2open1")
    },
)
