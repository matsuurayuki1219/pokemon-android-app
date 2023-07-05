package jp.matsuura.pokemon.androidapp.ui.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import jp.matsuura.pokemon.androidapp.R
import jp.matsuura.pokemon.androidapp.ext.showToast
import jp.matsuura.pokemon.androidapp.model.PokemonEvolutionModel
import jp.matsuura.pokemon.androidapp.model.PokemonType
import jp.matsuura.pokemon.androidapp.ui.common.PokemonInfoItem
import jp.matsuura.pokemon.androidapp.ui.common.PokemonItem
import jp.matsuura.pokemon.androidapp.ui.common.ProgressIndicator
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onButtonClicked: (Unit) -> Unit,
    onPokemonClicked: (String) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    DetailScreen(
        state = state,
        onButtonClicked = onButtonClicked,
        onPokemonClicked = onPokemonClicked,
    )
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is DetailScreenEvent.NetworkError -> {
                    context.showToast(message = context.getString(R.string.common_network_error))
                }
                is DetailScreenEvent.UnknownError -> {
                    context.showToast(message = context.getString(R.string.common_unknown_error))
                }
            }
        }
    }
}

@Composable
fun DetailScreen(
    state: DetailScreenState,
    onButtonClicked: (Unit) -> Unit,
    onPokemonClicked: (String) -> Unit,
) {
    if (state.isProgressVisible) ProgressIndicator()
    BackButton(
        onButtonClicked = onButtonClicked,
    )
    if (state.pokemonInfo == null) return

    Box(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).background(color = Color(0xffe6e6e6))) {
        HalfCircleItem(types = state.pokemonInfo.types)
        Column {
            JacketItem(
                id = state.pokemonInfo.id,
                name = state.pokemonInfo.enName,
                imageUri = state.pokemonInfo.imageUrl,
            )
            PokemonInfoItem(
                types = state.pokemonInfo.types,
                weight = state.pokemonInfo.weight,
                height = state.pokemonInfo.height,
            )
            PokemonEvolutionItems(
                pokemonList = state.pokemonInfo.evolutions,
                onPokemonClicked = onPokemonClicked,
            )
        }
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
                .background(Color.White),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_button),
                contentDescription = null,
                modifier = Modifier.padding(8.dp),
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 48.dp, end = 48.dp)
                    .aspectRatio(1.0f),
                model = imageUri,
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(top = 20.dp, start = 24.dp),
                text = "No.$id",
                fontSize = 20.sp,
            )
            Text(
                modifier = Modifier.padding(top = 12.dp, start = 24.dp),
                text = name,
                fontSize = 24.sp,
            )
        }
    }
}

@Composable
fun HalfCircleItem(
    types: List<PokemonType>,
) {
    val imageRes = getTypeHalfCircle(types = types)
    if (imageRes != null) {
        Image(
            painter = painterResource(id = imageRes),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

private fun getTypeHalfCircle(types: List<PokemonType>): Int? {
    return when (types.firstOrNull()) {
        PokemonType.BUG -> R.drawable.drawable_bug_color_half_circle
        PokemonType.DARK -> R.drawable.drawable_dark_color_half_circle
        PokemonType.DRAGON -> R.drawable.drawable_dragon_color_half_circle
        PokemonType.ELECTRIC -> R.drawable.drawable_electric_color_half_circle
        PokemonType.FAIRY -> R.drawable.drawable_fairy_color_half_circle
        PokemonType.FIGHTING -> R.drawable.drawable_fighting_color_half_circle
        PokemonType.FIRE -> R.drawable.drawable_fire_color_half_circle
        PokemonType.FLYING -> R.drawable.drawable_flying_color_half_circle
        PokemonType.GHOST -> R.drawable.drawable_ghost_color_half_circle
        PokemonType.GRASS -> R.drawable.drawable_grass_color_half_circle
        PokemonType.GROUND -> R.drawable.drawable_ground_color_half_circle
        PokemonType.ICE -> R.drawable.drawable_ice_color_half_circle
        PokemonType.NORMAL -> R.drawable.drawable_normal_color_half_circle
        PokemonType.POISON -> R.drawable.drawable_poison_color_half_circle
        PokemonType.PSYCHIC -> R.drawable.drawable_psychic_color_half_circle
        PokemonType.ROCK -> R.drawable.drawable_rock_color_half_circle
        PokemonType.STEEL -> R.drawable.drawable_steel_color_half_circle
        PokemonType.WATER -> R.drawable.drawable_water_color_half_circle
        else -> null
    }
}

@Composable
fun PokemonInfoItem(
    types: List<PokemonType>,
    weight: Int,
    height: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 24.dp),
    ) {
        PokemonInfoItem(
            imageRes = R.drawable.ic_pokemon_type,
            title = "Type",
            info = generateTypes(types = types),
        )
        Spacer(modifier = Modifier.padding(top = 12.dp))
        PokemonInfoItem(
            imageRes = R.drawable.ic_pokemon_type,
            title = "Height",
            info = (height / 10f).toString() + "m",
        )
        Spacer(modifier = Modifier.padding(top = 12.dp))
        PokemonInfoItem(
            imageRes = R.drawable.ic_pokemon_type,
            title = "Weight",
            info = (weight / 10f).toString() + "kg",
        )
    }
}

private fun generateTypes(types: List<PokemonType>): String {
    return if (types.isEmpty()) {
        "None"
    } else if (types.size == 1) {
        types.first().type
    } else {
        val result = StringBuilder()
        types.forEachIndexed { index, type ->
            if (index == 0) {
                result.append(type.type)
            } else {
                result.append("/${type.type}")
            }
        }
        result.toString()
    }
}

@Composable
fun PokemonEvolutionItems(
    pokemonList: List<PokemonEvolutionModel>,
    onPokemonClicked: (String) -> Unit,
) {
    Text(
        text = "Evolution",
        fontSize = 24.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 12.dp, start = 24.dp),
    )
    pokemonList.forEach {
        Column {
            PokemonItem(
                pokemonId = it.id.toString(),
                pokemonName = it.enName,
                imageUrl = it.imageUrl,
                onPokemonClicked = onPokemonClicked,
            )
        }
    }
    Spacer(modifier = Modifier.padding(top = 24.dp))
}
