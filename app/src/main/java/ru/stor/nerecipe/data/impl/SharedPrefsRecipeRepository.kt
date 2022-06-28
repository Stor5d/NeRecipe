package ru.stor.nerecipe.data.impl

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.stor.nerecipe.data.RecipeRepository
import ru.stor.nerecipe.classes.Recipe
import kotlin.properties.Delegates

class SharedPrefsRecipeRepository(
    application: Application
) : RecipeRepository {

    private val prefs = application.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private var nextId by Delegates.observable(
        prefs.getLong(NEXT_ID_PREFS_KEY, 0L)
    ) { _, _, newValue ->
        prefs.edit { putLong(NEXT_ID_PREFS_KEY, newValue) }
    }

    private var recipes
        get() = checkNotNull(data.value) {
            "Не нуль"
        }
        set(value) {
            prefs.edit {
                val serializedRecipes = Json.encodeToString(value)
                putString(RECIPES_PREFS_KEY, serializedRecipes)
            }
            data.value = value
        }

    override val data: MutableLiveData<List<Recipe>>

    init {
        val serializedRecipes = prefs.getString(RECIPES_PREFS_KEY, null)
        val recipes: List<Recipe> = if (serializedRecipes != null) {
            Json.decodeFromString(serializedRecipes)
        } else emptyList()
        data = MutableLiveData(recipes)
    }

    override fun insert(recipe: Recipe) {
        recipes = listOf(recipe.copy(id = ++nextId)) + recipes
    }

    override fun update(recipe: Recipe) {
        recipes = recipes.map {
            if (recipe.id == it.id) recipe else it
        }
    }

    override fun delete(recipeId: Long) {
        recipes = recipes.filterNot { recipe -> recipe.id == recipeId }
    }

    override fun move(startPosition: Int, endPosition: Int) {
        //Collections.swap(recipes, startPosition, endPosition)
    }

    override fun liked(recipeId: Long) {
        Log.e("AAA l",recipeId.toString())
        recipes = recipes.map { recipe ->
            if (recipe.id != recipeId) recipe
            else {
                recipe.copy(liked = !recipe.liked)
            }
        }

        val recipe = recipes.firstOrNull() {
            it.id==recipeId
        }
        Log.e("AAA l2",recipe?.liked.toString())
    }

    override fun saveStateSwitch(key: String, b: Boolean) {
        prefs.edit().putBoolean(key, b).apply()
    }

    override fun getStateSwitch(key: String): Boolean {
        return prefs.getBoolean(key, true)
    }


    companion object {
        const val RECIPES_PREFS_KEY = "posts"
        const val NEXT_ID_PREFS_KEY = "nextId"
    }
}