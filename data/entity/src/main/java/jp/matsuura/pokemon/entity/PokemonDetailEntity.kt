package jp.matsuura.pokemon.entity

import com.squareup.moshi.JsonClass
import jp.matsuura.pokemon.entity.generated.*

@JsonClass(generateAdapter = true)
data class PokemonDetailEntity(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    // Note: unknown type.
    // val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    // Note: unknown type.
    // val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)