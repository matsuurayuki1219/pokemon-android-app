package jp.matsuura.pokemon.androidapp.data.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Yellow(
    val back_default: String?,
    val back_gray: String?,
    val back_transparent: String?,
    val front_default: String?,
    val front_gray: String?,
    val front_transparent: String?
)