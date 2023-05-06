package jp.matsuura.pokemon.androidapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.pokemon.androidapp.model.PokemonType
import jp.matsuura.pokemon.androidapp.ui.common.ProgressIndicator
import kotlin.math.ceil

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    DetailScreen(
        state = state,
    )
}

@Composable
fun DetailScreen(
    state: DetailScreenState,
) {
    if (state.isProgressVisible) ProgressIndicator()
    if (state.pokemonInfo == null) return
    Column {
        JacketItem(
            id = state.pokemonInfo.id,
            name = state.pokemonInfo.name,
            imageUri = state.pokemonInfo.imageUrl,
        )
        PokemonTypeItems(types = state.pokemonInfo.types)
        BreedingItems(
            weight = state.pokemonInfo.weight,
            height = state.pokemonInfo.height,
        )
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
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color.Gray),
        ) {
            Text(
                text = type.name,
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