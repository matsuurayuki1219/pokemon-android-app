package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AbilityEntity(
    val ability: AbilityDetailEntity?,
    @Json(name = "is_hidden")
    val isHidden: Boolean?,
    val slot: Int?
)