package jp.matsuura.pokemon.androidapp.data.entity.generated

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerationIv(
    @Json(name = "diamond-pearl")
    val diamond_pearl: DiamondPearl,
    @Json(name = "heartgold-soulsilver")
    val heartgold_soulsilver: HeartgoldSoulsilver,
    val platinum: Platinum
)