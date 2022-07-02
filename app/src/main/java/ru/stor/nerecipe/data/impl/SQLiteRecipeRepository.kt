package ru.stor.nerecipe.data.impl

import androidx.lifecycle.map
import androidx.room.Transaction
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.db.RecipeDao

import ru.stor.nerecipe.data.RecipeRepository


class SQLiteRecipeRepository(
    private val dao: RecipeDao
) : RecipeRepository {

    override val data = dao.getAll().map { entities ->
        entities.map { it.toModel() }
    }

    override fun liked(recipeId: Long) {
        dao.likeById(recipeId)
    }

    override fun delete(recipeId: Long) {
        dao.removeById(recipeId)
    }

    override fun update(recipe: Recipe) {
        dao.updateContentById(recipe.id, recipe.title)
    }

    @Transaction
    override fun insert(recipe: Recipe) {
        val id = dao.insertRecipe(recipe.toEntity(0).recipeEntity)
        recipe.toEntity(id).stagesEntity.map {
            dao.insertStage(it)
        }

    }

}