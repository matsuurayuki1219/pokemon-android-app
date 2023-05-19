package jp.matsuura.pokemon.detail

import jp.matsuura.pokemon.model.PokemonDetailModel

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