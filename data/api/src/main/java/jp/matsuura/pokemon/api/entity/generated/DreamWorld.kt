package jp.matsuura.pokemon.api.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DreamWorld(
    val front_default: String?,
    val front_female: String?
)