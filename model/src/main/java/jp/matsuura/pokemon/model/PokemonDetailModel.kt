package jp.matsuura.pokemon.model

data class PokemonDetailModel(
    val id: Int,
    val enName: String,
    val jaName: String,
    val imageUrl: String,
    val types: List<PokemonType>,
    val weight: Int,
    val height: Int,
    val evolutions: List<PokemonEvolutionModel>
)