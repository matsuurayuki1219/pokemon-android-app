package jp.matsuura.pokemon.api.entity.generated

data class Chain(
    // val evolution_details: List<EvolutionDetail>,
    val evolves_to: List<Chain>,
    val is_baby: Boolean,
    val species: SpeciesXXX
)