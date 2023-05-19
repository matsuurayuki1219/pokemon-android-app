package jp.matsuura.pokemon.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationIi(
    val crystal: Crystal?,
    val gold: Gold?,
    val silver: Silver?,
)