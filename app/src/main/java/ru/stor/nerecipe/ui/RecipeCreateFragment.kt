package ru.stor.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import ru.stor.nerecipe.R
import ru.stor.nerecipe.adapter.StagesAdapter
import ru.stor.nerecipe.classes.Categories
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.databinding.RecipeCreateFragmentBinding
import ru.stor.nerecipe.viewModel.RecipeViewModel

class RecipeCreateFragment : Fragment() {
    private val viewModel by activityViewModels<RecipeViewModel>()

    private var filterList = arrayListOf<Int>()
    lateinit var binding: RecipeCreateFragmentBinding
    private var currentRecipe: Recipe? = null

    private fun onSaveButtonClicked(binding: RecipeCreateFragmentBinding) {
        val text = binding.titleEditText.text
        if (!text.isNullOrBlank()) {
            val resultBundle = Bundle(2)
            resultBundle.putString(TITLE_KEY, text.toString())
            resultBundle.putIntegerArrayList(CATEGORIES_KEY, filterList)
            setFragmentResult(REQUEST_KEY, resultBundle)
        }
        findNavController().navigate(R.id.feedFragment3)
    }

    private fun onCancelButtonClicked() {
        findNavController().navigate(R.id.feedFragment3)
    }

    private fun setFilter() {
        if (currentRecipe == null) {
            findNavController().navigate(
                R.id.action_recipeCreateFragment2_to_filterSetupFragment
            )
        } else {
            findNavController().navigate(
                R.id.action_recipeCreateFragment2_to_filterSetupFragment,
                bundleOf(FILTER_LIST_KEY to currentRecipe!!.categories)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = RecipeCreateFragmentBinding.inflate(
        layoutInflater, container, false
    ).also {
        binding = it

        val adapter = StagesAdapter(viewModel)
        val stageRecyclerView = binding.stageRecyclerView
        stageRecyclerView.adapter = adapter

        currentRecipe = null

        with(binding) {
            titleEditText.addTextChangedListener {
                if (titleEditText.text.isNullOrBlank()) {
                    titleTextView.visibility = View.GONE
                    titleEditText.textSize = 32F
                } else {
                    titleTextView.visibility = View.VISIBLE
                    titleEditText.textSize = 20F
                }
            }

            buttonSave.setOnClickListener { onSaveButtonClicked(binding) }
            buttonCancel.setOnClickListener { onCancelButtonClicked() }
            categoryViewStart.setOnClickListener {
                viewModel.setTitleCurrentRecipe(titleEditText.text.toString())
                setFilter()
            }
            categoryTextViewCaption.setOnClickListener {
                viewModel.setTitleCurrentRecipe(titleEditText.text.toString())
                setFilter()
            }
            categoryTextViewContent.setOnClickListener {
                viewModel.setTitleCurrentRecipe(titleEditText.text.toString())
                setFilter()
            }
            stageAddButton.setOnClickListener {
                viewModel.setTitleCurrentRecipe(titleEditText.text.toString())
                createStage()
            }
        }

        setFragmentResultListener(
            requestKey = StageCreateFragment.ADD_STAGE_REQUEST_KEY
        ) { requestKey, bundle ->
            if (requestKey != StageCreateFragment.ADD_STAGE_REQUEST_KEY) return@setFragmentResultListener
            val content = bundle.getString(
                StageCreateFragment.STAGE_CONTENT_KEY
            ) ?: return@setFragmentResultListener
            val uriPhoto = bundle.getString(StageCreateFragment.STAGE_URI_PHOTO_KEY)
            viewModel.onSaveStageClicked(content = content, uriPhoto = uriPhoto)
        }

        viewModel.getCurrentRecipe().observe(viewLifecycleOwner) { recipe ->
            if (recipe != null) {
                currentRecipe = recipe
                bind(recipe)
                adapter.submitList(recipe.stages)
            }
        }
    }.root

    private fun bind(recipe: Recipe?) {
        if (recipe != null) {
            binding.titleEditText.setText(recipe.title)
            binding.categoryTextViewContent.text = getCategoriesText(recipe.categories)
        }
    }

    private fun createStage() {
        findNavController().navigate(R.id.action_recipeCreateFragment2_to_stageCreateFragment)
    }

    companion object {

        fun getCategoriesText(list: List<Int>): String {
            var text =
                if (list.contains(Categories.European.id)) Categories.European.key + "\n" else ""
            text += if (list.contains(Categories.Asian.id)) Categories.Asian.key + "\n" else ""
            text += if (list.contains(Categories.PanAsian.id)) Categories.PanAsian.key + "\n" else ""
            text += if (list.contains(Categories.Eastern.id)) Categories.Eastern.key + "\n" else ""
            text += if (list.contains(Categories.American.id)) Categories.American.key + "\n" else ""
            text += if (list.contains(Categories.Russian.id)) Categories.Russian.key + "\n" else ""
            text += if (list.contains(Categories.Mediterranean.id)) Categories.Mediterranean.key + "\n" else ""
            text = text.removeSuffix("\n")
            return text
        }

        const val TITLE_KEY = "titleKey"
        const val CATEGORIES_KEY = "categoriesKey"
        const val REQUEST_KEY = "requestKey"
        const val FILTER_LIST_KEY = "filterListKey"

    }
}