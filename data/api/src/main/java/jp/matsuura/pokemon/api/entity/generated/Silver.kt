package jp.matsuura.pokemon.api.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Silver(
    val back_default: String?,
    val back_shiny: String?,
    val front_default: String?,
    val front_shiny: String?,
    val front_transparent: String?,
)