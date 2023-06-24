package jp.matsuura.pokemon.androidapp.ext

fun String.extractLastPathFromUrl(): Int {
    val segments = split("/").filter { it.isNotBlank() }
    return segments.last().toInt()
}