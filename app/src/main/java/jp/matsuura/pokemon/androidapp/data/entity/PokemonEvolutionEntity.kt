package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonEvolutionEntity(
    val chain: ChainEntity,
    val id: Int,
)
