package ru.stor.nerecipe.classes

import kotlinx.serialization.Serializable
import ru.stor.nerecipe.db.StageEntity

@Serializable
data class Stage(
    val id: Int,
    val recipeId: Long,
    val content: String,
    val uriPhoto: String? = null
) {
    internal fun Stage.toEntity() = StageEntity(
        id = id,
        recipeId = recipeId,
        content = content,
        uriPhoto = uriPhoto,
    )
}