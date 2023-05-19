package jp.matsuura.pokemon.entity.generated

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationIii(
    val emerald: Emerald?,
    @Json(name = "firered-leafgree")
    val firered_leafgreen: FireredLeafgreen?,
    @Json(name = "ruby-sapphire")
    val ruby_sapphire: RubySapphire?,
)