package jp.matsuura.pokemon.androidapp.ui.home

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import jp.matsuura.pokemon.androidapp.model.PokemonModel
import jp.matsuura.pokemon.androidapp.ui.common.ProgressIndicator

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Text(text = "Home Screen")
    val state by viewModel.uiState.collectAsState()
    HomeScreen(state = state)
}

@Composable
fun HomeScreen(
    state: HomeScreenState,
) {
    if (state.isLoading) ProgressIndicator()
    PokemonListItem(pokemonList = state.pokemonList)
}

@Composable
fun PokemonListItem(
    pokemonList: List<PokemonModel>,
) {
   LazyVerticalGrid(columns = GridCells.Fixed(3)) {
       items(pokemonList) { pokemon ->
           PokemonItem(pokemon = pokemon)
       }
   }
}

@Composable
fun PokemonItem(
    pokemon: PokemonModel,
) {
    AsyncImage(
        model = pokemon.imageUrl,
        contentDescription = null,
    )
}