package jp.matsuura.pokemon.androidapp.data.entity.generated

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Versions(
    @Json(name = "generation-i")
    val generation1: GenerationI?,
    @Json(name = "generation-ii")
    val generation2: GenerationIi?,
    @Json(name = "generation-iii")
    val generation3: GenerationIii?,
    @Json(name = "generation-iv")
    val generation4: GenerationIv?,
    @Json(name = "generation-v")
    val generation5: GenerationV?,
    @Json(name = "generation-vi")
    val generation6: GenerationVi?,
    @Json(name = "generation-vii")
    val generation7: GenerationVii?,
    @Json(name = "generation-viii")
    val generation8: GenerationViii?
)