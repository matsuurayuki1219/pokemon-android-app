package jp.matsuura.pokemon.androidapp.model

data class PokemonDetailModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<PokemonType>,
    val weight: Int,
    val height: Int,
    val evolutions: List<PokemonEvolutionModel>
)