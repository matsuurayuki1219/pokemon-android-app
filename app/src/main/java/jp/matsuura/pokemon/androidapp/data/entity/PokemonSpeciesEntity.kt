package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSpeciesEntity(
    @Json(name = "evolution_chain")
    val evolutionChain: EvolutionChainEntity,
    val names: List<NameEntity>,
)
