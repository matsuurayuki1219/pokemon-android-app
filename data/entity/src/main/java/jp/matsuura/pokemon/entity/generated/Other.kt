package jp.matsuura.pokemon.entity.generated

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Other(
    val dream_world: DreamWorld?,
    val home: Home?,
    @Json(name = "official-artwork")
    val official_artwork: OfficialArtwork?,
)