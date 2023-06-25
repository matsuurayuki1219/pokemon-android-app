package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonEntity(
    val count: Int,
    val next: String?,
    val previous: String?,
    @Json(name = "results")
    val pokemonList: List<PokemonInfoEntity>,
)
