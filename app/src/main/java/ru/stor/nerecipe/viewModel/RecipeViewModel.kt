package ru.stor.nerecipe.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.stor.nerecipe.data.RecipeRepository
import ru.stor.nerecipe.data.impl.SharedPrefsRecipeRepository
import ru.stor.nerecipe.adapter.RecipeInteractionListener
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.classes.Stage
import ru.stor.nerecipe.util.SingleLiveEvent
import java.util.*

class RecipeViewModel(application: Application) : AndroidViewModel(application),
    RecipeInteractionListener {

    private val repository: RecipeRepository = SharedPrefsRecipeRepository(application)
    //private var currentRecipeId: Long = 0

    val data by repository::data

    override fun onEditClicked(recipe: Recipe) {
        currentRecipe.value = recipe
        navigateToRecipeEditorScreenEvent.value = recipe
    }


    //    val sharePostContent = SingleLiveEvent<String>()
    val navigateToRecipeEditorScreenEvent = SingleLiveEvent<Recipe>()

    //    val playPostVideo = SingleLiveEvent<String>()
    val navigateToRecipeEvent = SingleLiveEvent<Long>()

    //    private var currentPostId: Long = -1
    val currentRecipe = SingleLiveEvent<Recipe>()

    fun onSaveRecipe(recipe: Recipe) {
        repository.insert(recipe)
        currentRecipe.value = null
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
    override fun onRemoveClicked(recipe: Recipe) = repository.delete(recipe.id)

    //
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

    fun onMove(startPosition: Int, endPosition: Int) {
        repository.move(startPosition, endPosition)
    }

    fun onAddRecipeClicked() {
        //navigateToPostContentScreenEvent.call()
    }
//
//    fun onLikeClickedPost() {
//        repository.liked(currentPostId)
//    }
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