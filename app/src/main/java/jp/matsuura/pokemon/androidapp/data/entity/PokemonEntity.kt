package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListEntity(
    val count: Int,
    @Json(name = "next")
    val nextUrl: String?,
    @Json(name = "previous")
    val previousUrl: String?,
    val results: List<PokemonEntity>,
)

@JsonClass(generateAdapter = true)
data class PokemonEntity(
    val name: String,
    val url: String,
)