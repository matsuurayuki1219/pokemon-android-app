package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TypeEntity(
    val slot: Int?,
    val type: TypeDetailEntity,
)
