package jp.matsuura.pokemon.androidapp.ui.detail

import jp.matsuura.pokemon.androidapp.model.PokemonDetailModel

data class DetailScreenState(
    val isProgressVisible: Boolean,
    val pokemonInfo: PokemonDetailModel?,
) {
    companion object {
        fun initValue() = DetailScreenState(
            isProgressVisible = false,
            pokemonInfo = null,
        )
    }
}
