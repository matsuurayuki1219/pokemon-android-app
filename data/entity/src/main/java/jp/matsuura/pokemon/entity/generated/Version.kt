package jp.matsuura.pokemon.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Version(
    val name: String?,
    val url: String?,
)