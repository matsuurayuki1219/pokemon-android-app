package jp.matsuura.pokemon.androidapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import jp.matsuura.pokemon.androidapp.model.PokemonModel
import jp.matsuura.pokemon.androidapp.ui.common.ProgressIndicator

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    HomeScreen(state = state)
}

@Composable
fun HomeScreen(
    state: HomeScreenState,
) {
    Title()
    if (state.isLoading) ProgressIndicator()
    PokemonListItem(pokemonList = state.pokemonList)
}

@Composable
fun Title() {
    Text(
        text = "ポケモン図鑑",
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun PokemonListItem(
    pokemonList: List<PokemonModel>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(top = 80.dp),
    ) {
        items(pokemonList) { pokemon ->
            PokemonItem(pokemon = pokemon)
        }
    }
}

@Composable
fun PokemonItem(
    pokemon: PokemonModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = null,
                modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp)
            )
            Text(
                text = "No.${pokemon.id}",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
            )
            Text(
                text = pokemon.name,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 16.dp),
            )
        }
    }
}