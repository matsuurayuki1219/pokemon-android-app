package jp.matsuura.pokemon.androidapp.model

import androidx.compose.ui.graphics.Color
import jp.matsuura.pokemon.androidapp.ui.theme.*

enum class PokemonType(val type: String, val color: Color) {
    FIRE("fire", Fire),
    WATER("water", Water),
    GRASS("grass", Grass),
    NORMAL("normal", Normal),
    ELECTRIC("electric", Electric),
    ICE("ice", Ice),
    FIGHTING("fighting", Fighting),
    POISON("poison", Poison),
    GROUND("ground", Ground),
    FLYING("flying", Flying),
    PSYCHIC("psychic", Psychic),
    BUG("bug", Bug),
    ROCK("rock", Rock),
    GHOST("ghost", Ghost),
    DRAGON("dragon", Dragon),
    DARK("dark", Dark),
    STEEL("steel", Steel),
    FAIRY("fairy", Fairy),
}