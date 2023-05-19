package jp.matsuura.pokemon.api.entity

import jp.matsuura.pokemon.api.entity.generated.Chain

data class PokemonEvolutionEntity(
    // val baby_trigger_item: Any?,
    val chain: Chain,
    val id: Int
)