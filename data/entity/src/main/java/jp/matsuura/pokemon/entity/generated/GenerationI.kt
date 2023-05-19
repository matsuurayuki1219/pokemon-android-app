package jp.matsuura.pokemon.entity.generated

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationI(
    @Json(name = "red-blue")
    val red_blue: RedBlue?,
    val yellow: Yellow?,
)