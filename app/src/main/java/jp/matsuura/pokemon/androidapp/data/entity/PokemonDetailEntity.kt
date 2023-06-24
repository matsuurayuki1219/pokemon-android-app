package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetailEntity(
    val abilities: List<AbilityEntity>,
    val height: Int,
    val name: String,
    val sprites: SpritesEntity,
    val types: List<TypeEntity>,
    val weight: Int
)