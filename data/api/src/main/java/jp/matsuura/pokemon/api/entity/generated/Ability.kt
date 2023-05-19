package jp.matsuura.pokemon.api.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ability(
    val ability: AbilityX?,
    val is_hidden: Boolean?,
    val slot: Int?
)