package jp.matsuura.pokemon.androidapp.data.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Home(
    val front_default: String,
    val front_female: String?,
    val front_shiny: String,
    val front_shiny_female: String?
)