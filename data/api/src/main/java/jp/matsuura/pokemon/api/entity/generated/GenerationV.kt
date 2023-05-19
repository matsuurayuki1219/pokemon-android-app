package jp.matsuura.pokemon.api.entity.generated

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationV(
    @Json(name = "black-white")
    val black_white: BlackWhite?,
)