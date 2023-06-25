package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChainEntity(
    @Json(name = "evolves_to")
    val evolvesTo: List<ChainEntity>,
    @Json(name = "is_baby")
    val isBaby: Boolean,
    val species: SpeciesDetailEntity,
)
