package dev.s7a.spigot.command.internal

import dev.s7a.spigot.command.KtCommandTabCompleteParameter
import dev.s7a.spigot.command.KtCommandTabCompleterTree
import dev.s7a.spigot.command.KtCommandTabCompleterType
import org.bukkit.command.CommandSender

/**
 * [KtCommandTabCompleterTree] のルートであり、実際にタブ補完を行うクラス
 *
 * @since 1.0.0
 */
internal class KtCommandTabCompleter : KtCommandTabCompleterTree() {
    fun complete(sender: CommandSender, args: Array<out String>): List<String> {
        val parameter = KtCommandTabCompleteParameter(sender, args.toList())
        var tree: KtCommandTabCompleterTree = this
        var excludes = listOf<String>()
        for (i in 0 until args.lastIndex) {
            val lower = args[i].lowercase()
            val (candidate, child) = tree.list.firstOrNull { (candidate) ->
                when (candidate) {
                    is KtCommandTabCompleterCandidate.Literal -> {
                        candidate.list.keys.contains(lower)
                    }
                    is KtCommandTabCompleterCandidate.Dynamic -> {
                        candidate.action.complete(parameter)?.map(String::lowercase)?.contains(lower) ?: false
                    }
                    is KtCommandTabCompleterCandidate.Default -> {
                        true
                    }
                }
            } ?: return emptyList()
            when (candidate.type) {
                KtCommandTabCompleterType.Single -> {
                    tree = child ?: return emptyList()
                }
                KtCommandTabCompleterType.Multiple -> {
                    break
                }
                KtCommandTabCompleterType.NoDuplication -> {
                    excludes = args.drop(i)
                    break
                }
            }
        }
        val argLastLower = args.last().lowercase()
        return buildList {
            tree.list.forEach { (candidate) ->
                when (candidate) {
                    is KtCommandTabCompleterCandidate.Literal -> {
                        candidate.list.forEach { (lower, origin) ->
                            if (excludes.contains(lower).not() && lower.startsWith(argLastLower)) {
                                addAll(origin)
                            }
                        }
                    }
                    is KtCommandTabCompleterCandidate.Dynamic -> {
                        candidate.action.complete(parameter)?.forEach { origin ->
                            val lower = origin.lowercase()
                            if (excludes.contains(lower).not() && lower.startsWith(argLastLower)) {
                                add(origin)
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}
