package jp.matsuura.pokemon.androidapp.data.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val name: String,
    val url: String
)