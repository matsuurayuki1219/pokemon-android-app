package jp.matsuura.pokemon.androidapp.ui.home

sealed interface HomeScreenEvent {
    object NetworkError: HomeScreenEvent
    data class UnknownError(val error: Throwable): HomeScreenEvent
}