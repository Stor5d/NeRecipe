package ru.stor.nerecipe.viewModel

import android.app.Application
import android.icu.text.Transliterator
import android.util.Log
import androidx.lifecycle.*
import ru.stor.nerecipe.data.RecipeRepository
import ru.stor.nerecipe.adapter.RecipeInteractionListener
import ru.stor.nerecipe.adapter.StageInteractionListener
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.classes.Stage
import ru.stor.nerecipe.data.impl.SQLiteRecipeRepository
import ru.stor.nerecipe.db.AppDb
import ru.stor.nerecipe.util.SingleLiveEvent

class RecipeViewModel(application: Application) : AndroidViewModel(application),
    RecipeInteractionListener, StageInteractionListener {

    private val repository: RecipeRepository = SQLiteRecipeRepository(
        dao = AppDb.getInstance(
            context = application
        ).recipeDao
    )

    val data by repository::data

    // val data: MutableLiveData<List<Recipe>> = repository.data as MutableLiveData<List<Recipe>>
    val filtratedData: MediatorLiveData<List<Recipe>> = MediatorLiveData()
    val searchQuery: MutableLiveData<String> = MutableLiveData()
    val categoriesList: MutableLiveData<List<Int>> = MutableLiveData()
    var favoriteFilter: MutableLiveData<Boolean> = MutableLiveData()
    val navigateToRecipeEditorScreenEvent = SingleLiveEvent<Recipe>()
    val navigateToRecipeEvent = SingleLiveEvent<Long>()
    val currentRecipe = SingleLiveEvent<Recipe>()
    private val stages: MutableLiveData<List<Stage>> = MutableLiveData()
    private var currentStages: MutableList<Stage> = mutableListOf()
    private var stageNextId = 0

    fun getStages(): LiveData<List<Stage>> {
        return stages
    }

    fun addStage(content: String, uriPhoto: String?) {
        if (content.isNotBlank()) {
            stageNextId++
            val stage =
                Stage(id = stageNextId, recipeId = 0, content = content, uriPhoto = uriPhoto)
            currentStages.add(stage)
            stages.value = currentStages
        }
    }


    init {
//        favoriteFilter.value = false
//        categoriesList.value = updateCategoryList()
//
//        filtratedData.addSource(data) {
//            multiFilter()
//        }
//        filtratedData.addSource(searchQuery) {
//            multiFilter()
//        }
//        filtratedData.addSource(categoriesList) {
//            multiFilter()
//        }
//        filtratedData.addSource(favoriteFilter) {
//            multiFilter()
//        }
    }


//    private fun updateCategoryList(): List<Int> {
//        val list = mutableListOf<Int>()
//        if (repository.getStateSwitch(FilterFragmentSwitch.KEY_STATE_SWITCH_EUROPEAN)) list.add(0)
//        if (repository.getStateSwitch(FilterFragmentSwitch.KEY_STATE_SWITCH_ASIAN)) list.add(1)
//        if (repository.getStateSwitch(FilterFragmentSwitch.KEY_STATE_SWITCH_PAN_ASIAN)) list.add(2)
//        if (repository.getStateSwitch(FilterFragmentSwitch.KEY_STATE_SWITCH_EASTERN)) list.add(3)
//        if (repository.getStateSwitch(FilterFragmentSwitch.KEY_STATE_SWITCH_AMERICAN)) list.add(4)
//        if (repository.getStateSwitch(FilterFragmentSwitch.KEY_STATE_SWITCH_RUSSIAN)) list.add(5)
//        if (repository.getStateSwitch(FilterFragmentSwitch.KEY_STATE_SWITCH_MEDITERRSNEAN)) list
//            .add(6)
//        return list
//    }

//    fun saveStateSwitch(key: String, b: Boolean) {
//        repository.saveStateSwitch(key, b)
//        categoriesList.value = updateCategoryList()
//    }
//
//    fun getStateSwitch(key: String): Boolean {
//        return repository.getStateSwitch(key)
//    }

//    private fun multiFilter() {
//        val searchText = searchQuery.value?.lowercase(Locale.getDefault())?.trim { it <= ' ' } ?: ""
//        if (searchText.isNotEmpty() || data.value != null || categoriesList.value != null) {
//            val list = data.value?.filter { recipe ->
//                recipe.title.lowercase(Locale.getDefault()).contains(searchText)
//            }?.filter {
//                it.categories.forEach { categoryId ->
//                    if (categoriesList.value!!.contains(categoryId)) return@filter true
//                }
//                return@filter false
//            }
//            val listFavorite = if (favoriteFilter.value==true) list?.filter{recipe->
//                recipe.liked
//            } else list
//            filtratedData.value = listFavorite
//        } else {
//            filtratedData.value = data.value
//        }
//    }

    override fun onEditClicked(recipe: Recipe) {
        currentRecipe.value = recipe
        navigateToRecipeEditorScreenEvent.value = recipe
    }

    fun onSaveRecipe(recipe: Recipe) {
        repository.insert(recipe)
        currentRecipe.value = null
    }

    fun setupSwitchSettingsCurrentRecipe(list: List<Int>) {
        currentRecipe.value = currentRecipe.value?.copy(categories = list)
    }

    //        fun onSaveButtonClicked(content: String) {
//        if (content.isBlank()) return
//        if(currentPostId==-1L) {
//            repository.insert(Post(
//                id = PostRepository.NEW_POST_ID,
//                author = "Me",
//                content = content,
//                published = "Today"
//            ))
//        } else {
//            getPost(currentPostId)?.copy(content = content)?.let { repository.update(it) }
//        }
//        currentPostId = -1
//    }
//
//    override fun onLikeClicked(post: Post) = repository.liked(post.id)
//
//    override fun onShareClicked(post: Post) {
//        repository.share(post.id)
//        sharePostContent.value = post.content
//    }
//
    override fun onRemoveClicked(recipeId: Long) = repository.delete(recipeId)

    //    override fun onEditClicked(post: Post) {
//        currentPostId = post.id
//        navigateToPostContentScreenEvent.value = post.content
//    }
//
//    override fun onPlayClicked(post: Post) {
//        playPostVideo.value = post.urlVideo
//    }
//
    override fun onToRecipe(recipe: Recipe) {
        navigateToRecipeEvent.value = recipe.id
        currentRecipe.value = recipe
    }

//    fun onMove(startPosition: Int, endPosition: Int) {
//        repository.move(startPosition, endPosition)
//    }

    fun onAddRecipeClicked() {
        //navigateToPostContentScreenEvent.call()
    }


    override fun onLikeClicked(recipeId: Long) {
        repository.liked(recipeId)
    }

    override fun onRemoveClicked(stage: Stage) {
        Log.e("AAAA stageId", stage.id.toString())
        currentStages = currentStages.filterNot { it.id == stage.id } as MutableList<Stage>
        stages.value = currentStages
    }

    override fun onUpClicked(stage: Stage) {
        TODO("Not yet implemented")
    }

    override fun onDownClicked(stage: Stage) {
        TODO("Not yet implemented")
    }
//
//    fun onShareClickedPost() {
//        repository.share(currentPostId)
//        sharePostContent.value = getPost(currentPostId)?.content
//    }
//
//
//    fun onRemoveClickedPost() {
//        repository.delete(currentPostId)
//    }
//
//    fun onEditClickedPost() {
//        navigateToPostContentScreenEvent.value = getPost(currentPostId)?.content
//    }
//
//    fun onPlayClickedPost() {
//        playPostVideo.value = getPost(currentPostId)?.urlVideo
//    }
//
//    private fun getPost(postId: Long) = data.value?.firstOrNull { post ->
//        post.id == postId
//    }

}