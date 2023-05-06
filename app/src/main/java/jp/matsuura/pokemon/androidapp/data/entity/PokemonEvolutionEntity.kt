package jp.matsuura.pokemon.androidapp.data.entity

import jp.matsuura.pokemon.androidapp.data.entity.generated.Chain

data class PokemonEvolutionEntity(
    // val baby_trigger_item: Any?,
    val chain: Chain,
    val id: Int
)