package jp.matsuura.pokemon.androidapp.data.entity.generated

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationI(
    @Json(name = "red-blue")
    val red_blue: RedBlue?,
    val yellow: Yellow?,
)