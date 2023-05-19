package jp.matsuura.pokemon.entity.generated

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationVii(
    val icons: Icons?,
    @Json(name = "ultra-sun-ultra-moon")
    val ultra_sun_ultra_moon: UltraSunUltraMoon?
)