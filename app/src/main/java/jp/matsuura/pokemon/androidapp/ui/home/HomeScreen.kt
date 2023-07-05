package jp.matsuura.pokemon.androidapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import jp.matsuura.pokemon.androidapp.R
import jp.matsuura.pokemon.androidapp.ext.showToast
import jp.matsuura.pokemon.androidapp.model.PokemonModel
import jp.matsuura.pokemon.androidapp.ui.common.PokemonItem
import jp.matsuura.pokemon.androidapp.ui.common.ProgressIndicator
import java.io.IOException

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onPokemonClicked: (Int) -> Unit,
) {
    val pokemon = viewModel.pokemon.collectAsLazyPagingItems()
    HomeScreen(
        pokemon = pokemon,
        onPokemonClicked = { pokemonId ->
            onPokemonClicked(pokemonId.toInt())
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    pokemon: LazyPagingItems<PokemonModel>,
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
            when (val loadingState = pokemon.loadState.refresh) {
                LoadState.Loading -> {
                    ProgressIndicator()
                }
                is LoadState.Error -> {
                    val context = LocalContext.current
                    if (loadingState.error is IOException) {
                        context.showToast(message = context.getString(R.string.common_network_error))
                    } else {
                        context.showToast(message = context.getString(R.string.common_unknown_error))
                    }
                }
                else -> {
                    PokemonItems(
                        pokemon = pokemon,
                        onPokemonClicked = onPokemonClicked,
                    )
                }
            }
        }
    }
}

@Composable
fun PokemonItems(
    pokemon: LazyPagingItems<PokemonModel>,
    onPokemonClicked: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
    ) {
        itemsIndexed(pokemon) { _, pokemon ->
            pokemon?.let {
                PokemonItem(
                    pokemonId = it.id,
                    pokemonName = it.enName,
                    imageUrl = it.imageUrl,
                    onPokemonClicked = onPokemonClicked,
                )
            }
        }
    }
}
