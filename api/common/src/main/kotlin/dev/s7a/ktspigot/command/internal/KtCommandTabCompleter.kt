package dev.s7a.ktspigot.command.internal

import dev.s7a.ktspigot.command.KtCommandTabCompleteAction
import dev.s7a.ktspigot.command.KtCommandTabCompleteParameter
import dev.s7a.ktspigot.command.KtCommandTabCompleterTree
import dev.s7a.ktspigot.command.KtCommandTabCompleterType

/**
 * [KtCommandTabCompleterTree] のルートであり、実際にタブ補完を行うクラス
 *
 * @since 1.0.0
 */
internal class KtCommandTabCompleter<T> : KtCommandTabCompleterTree<T>() {
    @Suppress("UNCHECKED_CAST")
    fun complete(sender: T, args: Array<out String>): List<String> {
        val parameter = KtCommandTabCompleteParameter(sender, args.toList())
        var tree: KtCommandTabCompleterTree<T> = this
        var excludes = listOf<String>()
        for (i in 0 until args.lastIndex) {
            val lower = args[i].lowercase()
            val (candidate, child) = tree.list.firstOrNull { (candidate) ->
                when (candidate) {
                    is KtCommandTabCompleterCandidate.Literal -> {
                        candidate.list.keys.contains(lower)
                    }
                    is KtCommandTabCompleterCandidate.Dynamic<*> -> {
                        (candidate.action as KtCommandTabCompleteAction<T>).complete(parameter)?.map(String::lowercase)?.contains(lower) ?: false
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
                            if (excludes.contains(lower).not() && lower.contains(argLastLower)) {
                                addAll(origin)
                            }
                        }
                    }
                    is KtCommandTabCompleterCandidate.Dynamic<*> -> {
                        (candidate.action as KtCommandTabCompleteAction<T>).complete(parameter)?.forEach { origin ->
                            val lower = origin.lowercase()
                            if (excludes.contains(lower).not() && lower.contains(argLastLower)) {
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
