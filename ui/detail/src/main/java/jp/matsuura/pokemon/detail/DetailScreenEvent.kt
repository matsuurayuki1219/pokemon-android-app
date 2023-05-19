package jp.matsuura.pokemon.detail

sealed interface DetailScreenEvent {
    object NetworkError: DetailScreenEvent
    data class UnknownError(val error: Throwable): DetailScreenEvent
}