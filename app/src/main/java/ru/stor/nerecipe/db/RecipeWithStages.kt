package ru.stor.nerecipe.db

import androidx.room.Embedded
import androidx.room.Relation
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.classes.Stage


data class RecipeWithStages(

    @Embedded
    val recipeEntity: RecipeEntity,

    @Relation(parentColumn = "id", entityColumn = "recipe_id")
    val stagesEntity: List<StageEntity>

) {

    internal fun toModel() = Recipe(
        id = recipeEntity.id,
        title = recipeEntity.title,
        author = recipeEntity.author,
        liked = recipeEntity.liked,
        categories = recipeEntity.categories,
        stages = stagesEntity.map { stageEntity ->
            Stage(
                id = stageEntity.id,
                recipeId = recipeEntity.id,
                content = stageEntity.content,
                uriPhoto = stageEntity.uriPhoto
            )
        }
    )

}