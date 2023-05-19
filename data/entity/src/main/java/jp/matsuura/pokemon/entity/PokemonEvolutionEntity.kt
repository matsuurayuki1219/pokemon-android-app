package jp.matsuura.pokemon.entity

import jp.matsuura.pokemon.entity.generated.Chain

data class PokemonEvolutionEntity(
    // val baby_trigger_item: Any?,
    val chain: Chain,
    val id: Int
)