package jp.matsuura.pokemon.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stat(
    val base_stat: Int?,
    val effort: Int?,
    val stat: StatX?,
)