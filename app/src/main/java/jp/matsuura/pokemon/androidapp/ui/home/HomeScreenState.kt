package jp.matsuura.pokemon.androidapp.ui.home

import jp.matsuura.pokemon.model.PokemonModel

data class HomeScreenState(
    val isLoading: Boolean,
    val pokemonList: List<PokemonModel>,
) {
    companion object {
        fun initValue() = HomeScreenState(
            isLoading = false,
            pokemonList = emptyList(),
        )
    }
}