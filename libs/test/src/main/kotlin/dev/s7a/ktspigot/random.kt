package dev.s7a.ktspigot

private val playerNameChars = ('a'..'z') + ('A'..'Z') + ('0'..'9') + '_'

fun randomPlayerName() = randomPlayerName((3..16).random())

fun randomPlayerName(length: Int) = buildString(length) {
    repeat(length) {
        append(playerNameChars.random())
    }
}
