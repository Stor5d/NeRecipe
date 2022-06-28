package ru.stor.nerecipe.adapter

import ru.stor.nerecipe.classes.Recipe

interface RecipeInteractionListener {

    fun onRemoveClicked(recipeId: Long)
    fun onEditClicked(recipe: Recipe)
    fun onToRecipe(recipe: Recipe)
    fun onLikeClicked(recipeId: Long)
}