package ru.stor.nerecipe.ui

import android.os.Bundle
import android.util.Log
import android.view.*

import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.stor.nerecipe.R
import ru.stor.nerecipe.adapter.RecipesAdapter
import ru.stor.nerecipe.adapter.StagesAdapter
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.classes.Stage
import ru.stor.nerecipe.databinding.AddStageLayoutBinding.inflate
import ru.stor.nerecipe.databinding.FeedFragmentBinding
import ru.stor.nerecipe.viewModel.RecipeViewModel
import java.util.*
import java.util.zip.Inflater
import kotlin.collections.ArrayList

class FeedFragment : Fragment() {

    private val viewModel by activityViewModels<RecipeViewModel>()
    lateinit var binding: FeedFragmentBinding
    private lateinit var adapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        viewModel.sharePostContent.observe(this) { postContent ->
//            val intent = Intent().apply {
//                action = Intent.ACTION_SEND
//                putExtra(Intent.EXTRA_TEXT, postContent)
//                type = "text/plain"
//            }
//            val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
//            startActivity(shareIntent)
//        }

//        viewModel.playPostVideo.observe(this) { urlVideo ->
//            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlVideo)))
//        }

        setFragmentResultListener(
            requestKey = RecipeCreateFragment.REQUEST_KEY
        ) { requestKey, bundle ->
            if (requestKey != RecipeCreateFragment.REQUEST_KEY) return@setFragmentResultListener
            val titleRecipe = bundle.getString(
                RecipeCreateFragment.TITLE_KEY
            ) ?: return@setFragmentResultListener
            val categories = bundle.getIntegerArrayList(RecipeCreateFragment.CATEGORIES_KEY)
                ?: return@setFragmentResultListener
            val stage = Stage("Повесить утку")
            val recipe = Recipe(0, titleRecipe, "My", categories, stage)
            viewModel.onSaveRecipe(recipe)

        }

        viewModel.navigateToRecipeEditorScreenEvent.observe(this) { recipe ->
            val direction = FeedFragmentDirections.actionFeedFragmentToRecipeCreateFragment()
            findNavController().navigate(direction)
        }

        viewModel.navigateToRecipeEvent.observe(this) { recipeId ->
            val direction =
                FeedFragmentDirections.actionFeedFragmentToRecipeViewFragment(recipeId)
            findNavController().navigate(direction)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FeedFragmentBinding.inflate(
        layoutInflater, container, false
    ).also { it ->
        binding = it
        setHasOptionsMenu(true)
        adapter = RecipesAdapter(viewModel)
        val recipeRecyclerView = binding.recipeRecyclerView
        recipeRecyclerView.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { recipes ->
            //adapter.submitList(recipes as MutableList<Recipe>?)

            val itemTouchHelper = ItemTouchHelper(simpleCallback)
            itemTouchHelper.attachToRecyclerView(recipeRecyclerView)
            adapter.addData(recipes)

        }

        binding.buttonAddRecipe.setOnClickListener {
            //viewModel.onAddRecipeClicked()
            viewModel.currentRecipe.value = null
            findNavController().navigate(
                FeedFragmentDirections.actionFeedFragmentToRecipeCreateFragment()
            )
        }

        binding.bNaw.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Log.e("AAA", "home")
                    true
                }
                R.id.allRecipe -> {
                    Log.e("AAA", "All")
                    true
                }
                R.id.favorites -> {
                    Log.e("AAA", "favorites")
                    true
                }
                else -> true
            }

        }


    }.root

    private var simpleCallback =
        object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP.or(ItemTouchHelper.DOWN), 0) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val startPosition = viewHolder.adapterPosition
                val endPosition = target.adapterPosition
                viewModel.onMove(startPosition, endPosition)
                recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }
        }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter -> return true
            R.id.item3 -> return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }


}

