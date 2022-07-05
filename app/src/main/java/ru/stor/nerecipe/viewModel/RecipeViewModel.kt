package ru.stor.nerecipe.viewModel

import android.app.Application
import androidx.lifecycle.*
import ru.stor.nerecipe.data.RecipeRepository
import ru.stor.nerecipe.adapter.RecipeInteractionListener
import ru.stor.nerecipe.adapter.StageInteractionListener
import ru.stor.nerecipe.classes.Categories
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.classes.Stage
import ru.stor.nerecipe.data.impl.SQLiteRecipeRepository
import ru.stor.nerecipe.dataSettings.SettingsRepository
import ru.stor.nerecipe.dataSettings.SharedPrefsSettingsRepository
import ru.stor.nerecipe.db.AppDb
import ru.stor.nerecipe.util.SingleLiveEvent
import java.util.*

class RecipeViewModel(application: Application) : AndroidViewModel(application),
    RecipeInteractionListener, StageInteractionListener, SettingsRepository {

    private val repository: RecipeRepository = SQLiteRecipeRepository(
        dao = AppDb.getInstance(
            context = application
        ).recipeDao
    )
    private val repositorySettings: SettingsRepository = SharedPrefsSettingsRepository(application)

    val data: LiveData<List<Recipe>> = repository.data as MutableLiveData<List<Recipe>>

    val filtratedData: MediatorLiveData<List<Recipe>> = MediatorLiveData()
    val searchQuery: MutableLiveData<String> = MutableLiveData()
    val navigateToRecipeEditorScreenEvent = SingleLiveEvent<Recipe>()
    val navigateToRecipeEvent = SingleLiveEvent<Long>()
    private val currentRecipe = SingleLiveEvent<Recipe>()
    private val categoryList = mutableSetOf<Int>()

    private var currentStages: MutableList<Stage> = mutableListOf()
    private var stageNextId = 0

    private fun searchFilter() {
        val searchText = searchQuery.value?.lowercase(Locale.getDefault())?.trim { it <= ' ' } ?: ""
        if (searchText.isNotEmpty() || data.value != null) {
            filtratedData.value = data.value?.filter { recipe ->
                recipe.title.lowercase(Locale.getDefault()).contains(searchText)
            }
        }
    }

    init {
        val list = mutableSetOf<Int>()
        if (repositorySettings.getStateSwitch(Categories.European.key)) {
            list.add(Categories.European.id)
        }
        if (repositorySettings.getStateSwitch(Categories.Asian.key)) {
            list.add(Categories.Asian.id)
        }
        if (repositorySettings.getStateSwitch(Categories.PanAsian.key)) {
            list.add(Categories.PanAsian.id)
        }
        if (repositorySettings.getStateSwitch(Categories.Eastern.key)) {
            list.add(Categories.Eastern.id)
        }
        if (repositorySettings.getStateSwitch(Categories.American.key)) {
            list.add(Categories.American.id)
        }
        if (repositorySettings.getStateSwitch(Categories.Russian.key)) {
            list.add(Categories.Russian.id)
        }
        if (repositorySettings.getStateSwitch(Categories.Mediterranean.key)) {
            list.add(Categories.Mediterranean.id)
        }
        repository.setFilter(list)

        filtratedData.addSource(data) {
            searchFilter()
        }
        filtratedData.addSource(searchQuery) {
            searchFilter()
        }
    }

    fun getCurrentRecipe(): SingleLiveEvent<Recipe> {
        return currentRecipe
    }

    fun onInsertClicked() {
        val recipe = Recipe(0, "", "My", false, listOf(), listOf(0))
        currentRecipe.value = recipe
    }

    override fun onEditClicked(recipe: Recipe) {
        currentRecipe.value = recipe
        currentStages = recipe.stages as MutableList<Stage>
        navigateToRecipeEditorScreenEvent.value = recipe
    }

    fun onSaveRecipe(title: String) {
        val recipe = currentRecipe.value?.copy(title = title)
        if (recipe != null) {
            if (recipe.id == 0L) {
                repository.insert(recipe)
            } else {
                repository.update(recipe)
            }
        }
        stageNextId = 0
        //currentRecipe.value = null
    }

    fun setTitleCurrentRecipe(title: String) {
        currentRecipe.value = currentRecipe.value?.copy(title = title)
    }

    fun setupSwitchSettingsCurrentRecipe(list: List<Int>) {
        currentRecipe.value = currentRecipe.value?.copy(categories = list)
    }

    override fun onRemoveClicked(recipeId: Long) = repository.delete(recipeId)

    override fun onToRecipe(recipe: Recipe) {
        navigateToRecipeEvent.value = recipe.id
        currentRecipe.value = recipe
    }

    override fun onLikeClicked(recipeId: Long) {
        repository.liked(recipeId)
    }

    override fun onRemoveStageClicked(stage: Stage) {
        var currentStages: MutableList<Stage> = currentRecipe.value?.stages as MutableList<Stage>
        currentStages = currentStages.filterNot { it.id == stage.id }.toMutableList()
        currentRecipe.value = currentRecipe.value?.copy(stages = currentStages)
    }

    override fun onSaveStageClicked(content: String, uriPhoto: String?) {
        if (content.isNotBlank()) {
            stageNextId++
            val stage =
                Stage(id = stageNextId, recipeId = 0, content = content, uriPhoto = uriPhoto)
            currentStages = if (currentRecipe.value != null) {
                if (currentRecipe.value?.stages?.isNotEmpty() == true) {
                    currentRecipe.value?.stages as MutableList<Stage>
                } else mutableListOf()
            } else {
                mutableListOf()
            }
            currentStages.add(stage)
            currentRecipe.value = currentRecipe.value?.copy(stages = currentStages)
        }
    }

    override fun onMoveStageClicked(stage: Stage) {
        TODO("Not yet implemented")
    }

    override fun saveStateSwitch(key: String, b: Boolean) {
        setupCategories(key, b)
        repositorySettings.saveStateSwitch(key, b)
    }

    override fun getStateSwitch(key: String): Boolean {
        val b = repositorySettings.getStateSwitch(key)
        setupCategories(key, b)
        return b
    }

    private fun setupCategories(key: String, b: Boolean) {
        if (key == Categories.European.key) {
            if (b) {
                categoryList.add(Categories.European.id)
            } else {
                categoryList.remove(Categories.European.id)
            }
        }
        if (key == Categories.Asian.key) {
            if (b) {
                categoryList.add(Categories.Asian.id)
            } else {
                categoryList.remove(Categories.Asian.id)
            }
        }
        if (key == Categories.PanAsian.key) {
            if (b) {
                categoryList.add(Categories.PanAsian.id)
            } else {
                categoryList.remove(Categories.PanAsian.id)
            }
        }
        if (key == Categories.Eastern.key) {
            if (b) {
                categoryList.add(Categories.Eastern.id)
            } else {
                categoryList.remove(Categories.Eastern.id)
            }
        }
        if (key == Categories.American.key) {
            if (b) {
                categoryList.add(Categories.American.id)
            } else {
                categoryList.remove(Categories.American.id)
            }
        }
        if (key == Categories.Russian.key) {
            if (b) {
                categoryList.add(Categories.Russian.id)
            } else {
                categoryList.remove(Categories.Russian.id)
            }
        }
        if (key == Categories.Mediterranean.key) {
            if (b) {

                categoryList.add(Categories.Mediterranean.id)
            } else {

                categoryList.remove(Categories.Mediterranean.id)
            }
        }
        repository.setFilter(categoryList)
    }
}