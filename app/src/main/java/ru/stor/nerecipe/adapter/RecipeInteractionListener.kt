package ru.stor.nerecipe.adapter

import ru.stor.nerecipe.classes.Recipe

interface RecipeInteractionListener {

    fun onRemoveClicked(recipe: Recipe)
    fun onEditClicked(recipe: Recipe)
    fun onToRecipe(recipe: Recipe)
}