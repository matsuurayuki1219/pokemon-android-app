package jp.matsuura.pokemon.androidapp.data.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonEntity(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>
)