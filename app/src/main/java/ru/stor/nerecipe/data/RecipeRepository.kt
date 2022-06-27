package ru.stor.nerecipe.data

import androidx.lifecycle.LiveData
import ru.stor.nerecipe.classes.Recipe

interface RecipeRepository {

    val data:LiveData<List<Recipe>>

    fun insert(recipe: Recipe)
    fun update(recipe: Recipe)
    fun delete(recipeId:Long)
    fun move(startPosition: Int, endPosition: Int)
    fun elected(recipeId:Long)

    companion object{
        const val NEW_POST_ID = 0L
    }
}