package jp.matsuura.pokemon.androidapp.model

import androidx.compose.ui.graphics.Color
import jp.matsuura.pokemon.androidapp.ui.theme.*

enum class PokemonType(val type: String, val color: Color) {
    FIRE("FIRE", Fire),
    WATER("WATER", Water),
    GRASS("GRASS", Grass),
    NORMAL("NORMAL", Normal),
    ELECTRIC("ELECTRIC", Electric),
    ICE("ICE", Ice),
    FIGHTING("FIGHTING", Fighting),
    POISON("POISON", Poison),
    GROUND("GROUND", Ground),
    FLYING("FLYING", Flying),
    PSYCHIC("PSYCHIC", Psychic),
    BUG("BUG", Bug),
    ROCK("ROCK", Rock),
    GHOST("GHOST", Ghost),
    DRAGON("DRAGON", Dragon),
    DARK("DARK", Dark),
    STEEL("STEEL", Steel),
    FAIRY("FAIRY", Fairy),
}
