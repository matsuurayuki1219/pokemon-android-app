package jp.matsuura.pokemon.androidapp.data.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ability(
    val ability: AbilityX?,
    val is_hidden: Boolean?,
    val slot: Int?
)