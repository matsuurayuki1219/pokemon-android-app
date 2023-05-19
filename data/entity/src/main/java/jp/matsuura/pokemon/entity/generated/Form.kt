package jp.matsuura.pokemon.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Form(
    val name: String?,
    val url: String?
)