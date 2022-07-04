package ru.stor.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.stor.nerecipe.R
import ru.stor.nerecipe.adapter.ViewStagesAdapter
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.databinding.RecipeViewFragmentBinding
import ru.stor.nerecipe.viewModel.RecipeViewModel

class RecipeViewFragment : Fragment(R.layout.recipe_view_fragment) {

    private val viewModel by activityViewModels<RecipeViewModel>()

    private lateinit var binding: RecipeViewFragmentBinding
    private var currentRecipe: Recipe? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = RecipeViewFragmentBinding.inflate(
        layoutInflater, container, false
    ).also {
        binding = it

        val adapter = ViewStagesAdapter()
        val stageRecyclerView = binding.stageRecyclerView
        stageRecyclerView.adapter = adapter

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
            binding.titleEditText.text = recipe.title
            binding.categoryTextViewContent.text = RecipeCreateFragment.getCategoriesText(recipe.categories)
        }
    }
}

