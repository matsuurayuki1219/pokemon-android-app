package jp.matsuura.pokemon.androidapp.data.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OfficialArtwork(
    val front_default: String,
    val front_shiny: String?,
)