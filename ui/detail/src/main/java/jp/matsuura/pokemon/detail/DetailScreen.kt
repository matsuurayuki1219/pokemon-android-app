package jp.matsuura.pokemon.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import jp.matsuura.pokemon.common.ProgressIndicator
import jp.matsuura.pokemon.model.PokemonEvolutionModel
import jp.matsuura.pokemon.model.PokemonType
import kotlin.math.ceil

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onButtonClicked: (Unit) -> Unit,
    onPokemonClicked: (Int) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    DetailScreen(
        state = state,
        onButtonClicked = onButtonClicked,
        onPokemonClicked = onPokemonClicked,
    )
}

@Composable
fun DetailScreen(
    state: DetailScreenState,
    onButtonClicked: (Unit) -> Unit,
    onPokemonClicked: (Int) -> Unit,
) {
    if (state.isProgressVisible) ProgressIndicator()
    BackButton(
        onButtonClicked = onButtonClicked,
    )
    if (state.pokemonInfo == null) return
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        JacketItem(
            id = state.pokemonInfo.id,
            name = state.pokemonInfo.jaName,
            imageUri = state.pokemonInfo.imageUrl,
        )
        PokemonTypeItems(types = state.pokemonInfo.types)
        BreedingItems(
            weight = state.pokemonInfo.weight,
            height = state.pokemonInfo.height,
        )
        EvolutionItems(
            evolutionInfo = state.pokemonInfo.evolutions,
            onPokemonClicked = onPokemonClicked,
        )
    }
}

@Composable
fun BackButton(
    onButtonClicked: (Unit) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp)
            .zIndex(20f)
            .clickable { onButtonClicked(Unit) },
    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(40.dp))
                .background(Color.White)
        ) {
            Image(
                // FIXME: fix later.
                // painter = painterResource(id = R.drawable.ic_back_button),
                painter = painterResource(id = coil.base.R.drawable.abc_vector_test),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun JacketItem(
    id: Int,
    name: String,
    imageUri: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Green)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp),
            model = imageUri,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "No.$id",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = name,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun PokemonTypeItems(types: List<PokemonType>) {
    Text(
        modifier = Modifier.padding(top = 20.dp, bottom = 12.dp, start = 24.dp, end = 24.dp),
        text = "TYPE",
        fontSize = 20.sp,
        textAlign = TextAlign.Left,
    )
    Row(
        modifier = Modifier.padding(top = 4.dp, start = 24.dp, end = 24.dp),
    ) {
        types.forEach {
            PokemonTypeItem(type = it)
        }
    }

}

@Composable
fun PokemonTypeItem(type: PokemonType) {
    Row {
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp)),
        ) {
            Text(
                text = type.type,
                modifier = Modifier.padding(8.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.padding(end = 12.dp))
    }
}

@Composable
fun BreedingItems(weight: Int, height: Int) {
    Text(
        modifier = Modifier.padding(top = 20.dp, bottom = 12.dp, start = 24.dp, end = 24.dp),
        text = "BREEDING",
        fontSize = 20.sp,
        textAlign = TextAlign.Left,
    )
    Row(
        modifier = Modifier.padding(top = 4.dp, start = 24.dp, end = 24.dp),
    ) {
        BreedingItem(key = "Weight", value = "${ceil(weight * 0.1 * 10.0) / 10.0} kg")
        BreedingItem(key = "Height", value = "${ceil(height * 0.1 * 10.0) / 10.0} m")
    }
}

@Composable
fun BreedingItem(key: String, value: String) {
    Row {
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color.Gray),
        ) {
            Text(
                text = "$key: $value",
                modifier = Modifier.padding(8.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.padding(end = 12.dp))
    }
}

@Composable
fun EvolutionItems(
    evolutionInfo: List<PokemonEvolutionModel>,
    onPokemonClicked: (Int) -> Unit,
) {
    Text(
        modifier = Modifier.padding(top = 20.dp, bottom = 16.dp, start = 24.dp, end = 24.dp),
        text = "EVOLUTION",
        fontSize = 20.sp,
        textAlign = TextAlign.Left,
    )
    evolutionInfo.forEach {
        EvolutionItem(
            evolutionInfo = it,
            onPokemonClicked = onPokemonClicked,
        )
    }
}

@Composable
fun EvolutionItem(
    evolutionInfo: PokemonEvolutionModel,
    onPokemonClicked: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 12.dp)
    ) {
        Card(
            modifier = Modifier.clickable { onPokemonClicked(evolutionInfo.id) },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(12.dp)
            ) {
                AsyncImage(
                    model = evolutionInfo.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp),
                )
                Text(
                    text = evolutionInfo.jaName,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 16.dp),
                )
            }
        }
    }
}