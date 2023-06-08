package jp.matsuura.pokemon.androidapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import jp.matsuura.pokemon.androidapp.R
import jp.matsuura.pokemon.androidapp.ext.showToast
import jp.matsuura.pokemon.androidapp.model.PokemonModel
import jp.matsuura.pokemon.androidapp.ui.common.ProgressIndicator
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onCardItemClicked: (Int) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onCardItemClicked = { pokemonId ->
            viewModel.onCardItemClicked(pokemonId = pokemonId)
        }
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
                    onCardItemClicked.invoke(event.pokemonId)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeScreenState,
    onCardItemClicked: (String) -> Unit,
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
                    color = colorResource(id = R.color.gray90)
                )
        ) {
            if (state.isLoading) ProgressIndicator()
            PokemonItems(
                pokemonList = state.pokemonList,
                onCardItemClicked = onCardItemClicked,
            )
        }
    }
}

@Composable
fun PokemonItems(
    pokemonList: List<PokemonModel>,
    onCardItemClicked: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
    ) {
        items(pokemonList) { pokemon ->
            PokemonItem(
                pokemon = pokemon,
                onCardItemClicked = onCardItemClicked,
            )
        }
    }
}

@Composable
fun PokemonItem(
    pokemon: PokemonModel,
    onCardItemClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCardItemClicked.invoke(pokemon.id) }
                .padding(start = 36.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 60.dp, bottomStart = 60.dp)
                ),
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .padding(start = 24.dp, top = 12.dp, bottom = 12.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "No.${pokemon.id}",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp),
                )
                Text(
                    text = pokemon.jaName,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 18.dp),
                )
            }
        }
    }
}