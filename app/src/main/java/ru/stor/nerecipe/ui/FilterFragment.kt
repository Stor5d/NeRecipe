package ru.stor.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import ru.stor.nerecipe.adapter.RecipesAdapter
import ru.stor.nerecipe.classes.Categories
import ru.stor.nerecipe.databinding.FeedFragmentBinding
import ru.stor.nerecipe.databinding.FilterFragmentBinding
import ru.stor.nerecipe.viewModel.RecipeViewModel

class FilterFragment : Fragment() {

    private var filterList = arrayListOf<Int>()
    //private val viewModel by activityViewModels<RecipeViewModel>()

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

//        setFragmentResultListener(
//            requestKey = PostContentFragment.REQUEST_KEY
//        ) { requestKey, bundle ->
//            if (requestKey != PostContentFragment.REQUEST_KEY) return@setFragmentResultListener
//            val newPostContent = bundle.getString(
//                PostContentFragment.RESULT_KEY
//            ) ?: return@setFragmentResultListener
//            viewModel.onSaveButtonClicked(newPostContent)
//        }

//        viewModel.navigateToPostContentScreenEvent.observe(this) { initialContent ->
//            val direction = FeedFragmentDirections.toPostContentFragment(initialContent)
//            findNavController().navigate(direction)
//        }
//
//        viewModel.navigateToRecipeEvent.observe(this) { recipeId ->
//            val direction =
//                FeedFragmentDirections.actionFeedFragmentToRecipeViewFragment(recipeId)
//            findNavController().navigate(direction)
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FilterFragmentBinding.inflate(
        layoutInflater, container, false
    ).also { binding ->

        binding.checkboxEuropean.setOnClickListener { filterList = filterListCreate(binding) }
        binding.checkboxAsian.setOnClickListener { filterList = filterListCreate(binding) }
        binding.checkboxPanAsian.setOnClickListener { filterList = filterListCreate(binding) }
        binding.checkboxEastern.setOnClickListener { filterList = filterListCreate(binding) }
        binding.checkboxAmerican.setOnClickListener { filterList = filterListCreate(binding) }
        binding.checkboxRussian.setOnClickListener { filterList = filterListCreate(binding) }
        binding.checkboxEuropean.setOnClickListener { filterList = filterListCreate(binding) }
        binding.checkboxEuropean.setOnClickListener { filterList = filterListCreate(binding) }

//       val adapter = RecipesAdapter(viewModel)
//        binding.recipeRecyclerView.adapter = adapter
//        viewModel.data.observe(viewLifecycleOwner) { recipes ->
//            adapter.submitList(recipes)
//        }
////
//        binding.buttonAddRecipe.setOnClickListener {
//            //viewModel.onAddRecipeClicked()
//            findNavController().navigate(
//                FeedFragmentDirections.actionFeedFragmentToRecipeCreateFragment())
//        }

    }.root

    private fun filterListCreate(binding: FilterFragmentBinding): ArrayList<Int> {
        val filterList = arrayListOf<Int>()
        if (binding.checkboxEuropean.isSelected) filterList.add(Categories.European.id)
        if (binding.checkboxAsian.isSelected) filterList.add(Categories.Asian.id)
        if (binding.checkboxPanAsian.isSelected) filterList.add(Categories.PanAsian.id)
        if (binding.checkboxEastern.isSelected) filterList.add(Categories.Eastern.id)
        if (binding.checkboxAmerican.isSelected) filterList.add(Categories.American.id)
        if (binding.checkboxRussian.isSelected) filterList.add(Categories.Russian.id)
        if (binding.checkboxMediterranean.isSelected) filterList.add(Categories.Mediterranean.id)
        return filterList
    }

    override fun onDestroy() {
        val resultBundle = Bundle(1)
        resultBundle.putIntegerArrayList(FILTER_LIST_KEY,filterList)
        setFragmentResult(REQUEST_FILTER_KEY, resultBundle)
        super.onDestroy()
    }



    companion object {
        const val FILTER_LIST_KEY = "filterListKey"
        const val REQUEST_FILTER_KEY = "requestFilterKey"

    }

}

