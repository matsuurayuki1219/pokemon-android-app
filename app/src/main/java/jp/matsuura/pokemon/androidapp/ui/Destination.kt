package jp.matsuura.pokemon.androidapp.ui

sealed class Destination (val route: String) {
    object Home : Destination("home")
    object Detail : Destination("detail")
}