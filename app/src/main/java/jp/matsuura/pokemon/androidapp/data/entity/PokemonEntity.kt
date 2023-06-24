package jp.matsuura.pokemon.androidapp.data.entity

import com.squareup.moshi.JsonClass
import jp.matsuura.pokemon.androidapp.data.entity.generated.Result

@JsonClass(generateAdapter = true)
data class PokemonEntity(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>
)