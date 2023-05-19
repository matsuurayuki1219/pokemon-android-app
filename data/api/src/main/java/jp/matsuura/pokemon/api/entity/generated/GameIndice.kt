package jp.matsuura.pokemon.api.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameIndice(
    val game_index: Int?,
    val version: Version?,
)