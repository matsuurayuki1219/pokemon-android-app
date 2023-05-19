package jp.matsuura.pokemon.api.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationIi(
    val crystal: Crystal?,
    val gold: Gold?,
    val silver: Silver?,
)