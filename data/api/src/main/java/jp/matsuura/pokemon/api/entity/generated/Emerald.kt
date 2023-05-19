package jp.matsuura.pokemon.api.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Emerald(
    val front_default: String?,
    val front_shiny: String?,
)