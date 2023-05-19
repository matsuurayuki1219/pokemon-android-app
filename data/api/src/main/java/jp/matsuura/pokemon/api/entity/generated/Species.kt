package jp.matsuura.pokemon.api.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Species(
    val name: String?,
    val url: String?,
)