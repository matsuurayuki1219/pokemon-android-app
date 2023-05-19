package jp.matsuura.pokemon.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Move(
    val move: MoveX?,
    val version_group_details: List<VersionGroupDetail>
)