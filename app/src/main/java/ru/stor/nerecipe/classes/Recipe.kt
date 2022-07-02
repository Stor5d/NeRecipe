package ru.stor.nerecipe.classes

import kotlinx.serialization.Serializable
import ru.stor.nerecipe.db.RecipeEntity
import ru.stor.nerecipe.db.RecipeWithStages
import ru.stor.nerecipe.db.StageEntity

@Serializable
data class Recipe(
    val id: Long,
    val title: String,
    val author: String,
    val liked: Boolean = false,
    val stages: List<Stage>,
    val categories: List<Int>
) {

    internal fun toEntity(recipeId: Long) = RecipeWithStages(
        RecipeEntity(
            id = id,
            title = title,
            author = author,
            liked = liked,
            categories = categories
        ),
        stages.map { stage ->
            StageEntity(
                id = stage.id,
                recipeId = recipeId,
                content = stage.content,
                uriPhoto = stage.uriPhoto
            )
        }
    )

}