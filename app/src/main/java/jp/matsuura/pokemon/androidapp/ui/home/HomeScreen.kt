package jp.matsuura.pokemon.androidapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jp.matsuura.pokemon.androidapp.R
import jp.matsuura.pokemon.androidapp.ext.showToast
import jp.matsuura.pokemon.androidapp.model.PokemonModel
import jp.matsuura.pokemon.androidapp.ui.common.PokemonItem
import jp.matsuura.pokemon.androidapp.ui.common.ProgressIndicator
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onPokemonClicked: (Int) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onPokemonClicked = { pokemonId ->
            viewModel.onCardItemClicked(pokemonId = pokemonId)
        },
    )
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is HomeScreenEvent.NetworkError -> {
                    context.showToast(message = context.getString(R.string.common_network_error))
                }
                is HomeScreenEvent.UnknownError -> {
                    context.showToast(message = context.getString(R.string.common_unknown_error))
                }
                is HomeScreenEvent.NavigateToDetail -> {
                    onPokemonClicked.invoke(event.pokemonId)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeScreenState,
    onPokemonClicked: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.home_tool_bar))
                },
            )
        },
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .background(
                    color = colorResource(id = R.color.gray90),
                ),
        ) {
            if (state.isLoading) ProgressIndicator()
            PokemonItems(
                pokemonList = state.pokemonList,
                onPokemonClicked = onPokemonClicked,
            )
        }
    }
}

@Composable
fun PokemonItems(
    pokemonList: List<PokemonModel>,
    onPokemonClicked: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
    ) {
        items(pokemonList) { pokemon ->
            PokemonItem(
                pokemonId = pokemon.id,
                pokemonName = pokemon.jaName,
                imageUrl = pokemon.imageUrl,
                onPokemonClicked = onPokemonClicked,
            )
        }
    }
}
