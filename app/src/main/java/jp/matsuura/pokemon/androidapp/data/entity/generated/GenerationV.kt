package jp.matsuura.pokemon.androidapp.data.entity.generated

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationV(
    @Json(name = "black-white")
    val black_white: BlackWhite
)