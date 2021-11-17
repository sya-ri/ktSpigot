package dev.s7a.spigot.example.command

import dev.s7a.spigot.command.Dynamics
import dev.s7a.spigot.command.KtCommandTabCompleteAction
import dev.s7a.spigot.command.KtCommandTabCompleterTree
import dev.s7a.spigot.command.KtCommandTabCompleterType
import dev.s7a.spigot.command.Literals
import dev.s7a.spigot.command.ktCommand
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

/**
 * コマンド例
 */
fun JavaPlugin.exampleCommand() {
    ktCommand("example") {
        tabComplete {
            // プレイヤーからプレイヤーへテレポートします
            literal("teleport", "tp") {
                dynamicDefault(Dynamics.OnlinePlayers) {
                    dynamic(Dynamics.OnlinePlayers)
                }
            }
            // 指定した全アイテムを取得します
            literal("item") {
                literal(Literals.Materials)
            }
            // 指定した座標にブロックを設置します
            literal("setblock") {
                dynamicDefault({ (sender) ->
                    (sender as? Player)?.location?.blockX?.toString()?.let(::listOf)
                }) {
                    dynamicDefault({ (sender) ->
                        (sender as? Player)?.location?.blockY?.toString()?.let(::listOf)
                    }) {
                        dynamicDefault({ (sender) ->
                            (sender as? Player)?.location?.blockZ?.toString()?.let(::listOf)
                        }) {
                            literal(Literals.Blocks)
                        }
                    }
                }
            }
            // タブ補完のテスト
            literal("test") {
                literal("1", "2") {
                    literal("6") {
                        // 永遠と 9 が候補に出続ける
                        literal("9", type = KtCommandTabCompleterType.Multiple)
                    }
                }
                literal("3", "4") {
                    literal("7") {
                        // 10, 11 を入力しきるまで候補に出続ける
                        literal("10", "11", type = KtCommandTabCompleterType.NoDuplication)
                    }
                }
                literal("5") {
                    // 先に書いたものが優先される
                    literal("8") {
                        // 評価されない
                        dynamic({ listOf("12") }) {
                            literal("14")
                        }
                        // dynamic より literal が優先される
                        literal("12") {
                            literal("15") // "example test 5 8 12 15" と入力されたら仕様通り
                        }
                    }
                    // 評価されない
                    literal("8") {
                        literal("13")
                    }
                }
            }
        }
        execute { (sender, label, args) ->
            when (args.getOrNull(0)?.lowercase()) {
                "teleport", "tp" -> {
                    val (player, target) = when (args.size) {
                        1 -> {
                            sender.sendMessage("§b[Example] §cプレイヤー名を入力してください")
                            return@execute
                        }
                        2 -> {
                            if (sender is Player) {
                                val target = Bukkit.getPlayer(args[1]) ?: return@execute sender.sendMessage("§b[Example] §c${args[1]} という名前のプレイヤーは見つかりませんでした")
                                sender to target
                            } else {
                                sender.sendMessage("§b[Example] §cテレポート先のプレイヤー名を入力してください")
                                return@execute
                            }
                        }
                        else -> {
                            val player = Bukkit.getPlayer(args[1]) ?: return@execute sender.sendMessage("§b[Example] §c${args[1]} という名前のプレイヤーは見つかりませんでした")
                            val target = Bukkit.getPlayer(args[2]) ?: return@execute sender.sendMessage("§b[Example] §c${args[2]} という名前のプレイヤーは見つかりませんでした")
                            player to target
                        }
                    }
                    player.teleport(target)
                }
                "item" -> {
                    val player = sender as? Player ?: return@execute sender.sendMessage("§b[Example] §cプレイヤーからのみ実行できるコマンドです")
                    args.subList(1, args.size).forEach {
                        val material = Material.getMaterial(it.uppercase()) ?: return@forEach
                        player.inventory.addItem(ItemStack(material))
                    }
                }
                "setblock" -> {
                    val player = sender as? Player ?: return@execute sender.sendMessage("§b[Example] §cプレイヤーからのみ実行できるコマンドです")
                    val x = args.getOrNull(1)?.toIntOrNull() ?: return@execute sender.sendMessage("§b[Example] §cX 座標を入力してください")
                    val y = args.getOrNull(2)?.toIntOrNull() ?: return@execute sender.sendMessage("§b[Example] §cY 座標を入力してください")
                    val z = args.getOrNull(3)?.toIntOrNull() ?: return@execute sender.sendMessage("§b[Example] §cZ 座標を入力してください")
                    val block = args.getOrNull(4)?.uppercase()?.let(Material::getMaterial)?.takeIf(Material::isBlock) ?: return@execute sender.sendMessage("§b[Example] §c設置するブロックを入力してください")
                    player.world.getBlockAt(x, y, z).type = block
                }
                "test" -> {
                    sender.sendMessage("§b[Example] §f/$label ${args.joinToString(" ")}")
                }
                else -> {
                    sender.sendMessage(
                        """
                            
                        """.trimIndent()
                    )
                }
            }
        }
    }
}

/**
 * [KtCommandTabCompleterTree.dynamic] [KtCommandTabCompleterTree.default] を同時に登録する
 */
private fun KtCommandTabCompleterTree.dynamicDefault(action: KtCommandTabCompleteAction, child: KtCommandTabCompleterTree.() -> Unit) {
    dynamic(action, child)
    default(child)
}
