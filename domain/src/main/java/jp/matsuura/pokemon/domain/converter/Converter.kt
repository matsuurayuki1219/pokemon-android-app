package jp.matsuura.pokemon.domain.converter

import jp.matsuura.pokemon.api.entity.generated.Type
import jp.matsuura.pokemon.model.PokemonType

fun Type.toModel(): PokemonType {
    return when (type?.name) {
        "normal" -> PokemonType.NORMAL
        "fire" -> PokemonType.FIRE
        "water" -> PokemonType.WATER
        "electric" -> PokemonType.ELECTRIC
        "grass" -> PokemonType.GRASS
        "ice" -> PokemonType.ICE
        "fighting" -> PokemonType.FIGHTING
        "poison" -> PokemonType.POISON
        "ground" -> PokemonType.GROUND
        "flying" -> PokemonType.FLYING
        "psychic" -> PokemonType.PSYCHIC
        "bug" -> PokemonType.BUG
        "rock" -> PokemonType.ROCK
        "ghost" -> PokemonType.GHOST
        "dragon" -> PokemonType.DRAGON
        "dark" -> PokemonType.DARK
        "steel" -> PokemonType.STEEL
        "fairy" -> PokemonType.FAIRY
        else -> throw IllegalStateException("type is not defined.")
    }
}