package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NameEntity(
    val language: LanguageEntity,
    val name: String,
)
