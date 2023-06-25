package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OtherEntity(
    @Json(name = "dream_world")
    val dreamWorld: DreamWorldEntity?,
    val home: HomeEntity?,
    @Json(name = "official-artwork")
    val officialArtwork: OfficialArtworkEntity,
)
