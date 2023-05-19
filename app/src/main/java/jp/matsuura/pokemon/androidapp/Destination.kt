package jp.matsuura.pokemon.androidapp

sealed class Destination (val route: String) {
    object Home : Destination(route = "home")
    object Detail : Destination(route = "detail")
}