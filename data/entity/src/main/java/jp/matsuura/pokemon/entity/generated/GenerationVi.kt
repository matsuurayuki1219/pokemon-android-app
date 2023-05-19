package jp.matsuura.pokemon.entity.generated

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationVi(
    @Json(name = "omegaruby-alphasapphire")
    val omegaruby_alphasapphire: OmegarubyAlphasapphire?,
    @Json(name = "x-y")
    val x_y: XY?,
)