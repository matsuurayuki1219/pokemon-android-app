package jp.matsuura.pokemon.api.entity.generated

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VersionGroupDetail(
    val level_learned_at: Int?,
    val move_learn_method: MoveLearnMethod?,
    val version_group: VersionGroup?,
)