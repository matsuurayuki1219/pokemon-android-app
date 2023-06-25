package jp.matsuura.pokemon.androidapp.ui.detail

sealed interface DetailScreenEvent {
    object NetworkError : DetailScreenEvent
    data class UnknownError(val error: Throwable) : DetailScreenEvent
}
