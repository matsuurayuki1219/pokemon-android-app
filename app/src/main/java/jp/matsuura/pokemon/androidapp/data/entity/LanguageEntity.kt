package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LanguageEntity(
    val name: String,
    val url: String,
)