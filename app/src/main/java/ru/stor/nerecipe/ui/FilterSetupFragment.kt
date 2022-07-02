package ru.stor.nerecipe.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.*
import ru.stor.nerecipe.R
import ru.stor.nerecipe.classes.Categories
import ru.stor.nerecipe.databinding.FilterFragmentSwitchBinding
import ru.stor.nerecipe.viewModel.RecipeViewModel
import java.util.ArrayList

class FilterSetupFragment : Fragment(R.layout.filter_fragment_switch) {

    private var filterList = mutableListOf<Int>()
    private lateinit var binding: FilterFragmentSwitchBinding
    private val viewModel by activityViewModels<RecipeViewModel>()

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
    ) = FilterFragmentSwitchBinding.inflate(
        layoutInflater, container, false
    ).also { it ->
        binding = it

        val filterList = arguments?.getIntegerArrayList(RecipeCreateFragment.FILTER_LIST_KEY)
        Log.e("AAA filter", filterList.toString())

        if (filterList != null) {
            bind(filterList)
        }

        val l = arrayListOf<Int>(0, 1, 3)
        val x = 4
        Log.e("AAA sdsdsds", l.contains(x).toString())

        //viewModel.currentRecipe.observe(viewLifecycleOwner) { recipe ->

        //   }

//        with(binding) {
//            switchEuropean.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_EUROPEAN)
//            switchAsian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_ASIAN)
//            switchPanAsian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_PAN_ASIAN)
//            switchEastern.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_EASTERN)
//            switchAmerican.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_AMERICAN)
//            switchRussian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_RUSSIAN)
//            switchMediterranean.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_MEDITERRSNEAN)
//        }
//
//        with(binding) {
//
//            switchEuropean.setOnClickListener {
//                viewModel.saveStateSwitch(
//                    KEY_STATE_SWITCH_EUROPEAN,
//                    switchEuropean.isChecked
//                )
//            }
//            switchAsian.setOnClickListener {
//                viewModel.saveStateSwitch(
//                    KEY_STATE_SWITCH_ASIAN,
//                    switchAsian.isChecked
//                )
//            }
//            switchPanAsian.setOnClickListener {
//                viewModel.saveStateSwitch(
//                    KEY_STATE_SWITCH_PAN_ASIAN,
//                    switchPanAsian.isChecked
//                )
//            }
//            switchEastern.setOnClickListener {
//                viewModel.saveStateSwitch(
//                    KEY_STATE_SWITCH_EASTERN,
//                    switchEastern.isChecked
//                )
//            }
//            switchAmerican.setOnClickListener {
//                viewModel.saveStateSwitch(
//                    KEY_STATE_SWITCH_AMERICAN,
//                    switchAmerican.isChecked
//                )
//            }
//            switchRussian.setOnClickListener {
//                viewModel.saveStateSwitch(
//                    KEY_STATE_SWITCH_RUSSIAN,
//                    switchRussian.isChecked
//                )
//            }
//            switchMediterranean.setOnClickListener {
//                viewModel.saveStateSwitch(
//                    KEY_STATE_SWITCH_MEDITERRSNEAN,
//                    switchMediterranean.isChecked
//                )
//            }
//
//        }


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

    private fun bind(categories: ArrayList<Int>) {
        with(binding) {
            switchEuropean.isChecked = categories.contains(Categories.European.id)
            switchAsian.isChecked = (categories.contains(Categories.Asian.id))
            switchPanAsian.isChecked = categories.contains(Categories.PanAsian.id)
            switchEastern.isChecked = categories.contains(Categories.Eastern.id)
            switchAmerican.isChecked = categories.contains(Categories.American.id)
            switchRussian.isChecked = categories.contains(Categories.Russian.id)
            switchMediterranean.isChecked = categories.contains(Categories.Mediterranean.id)
        }
    }

    private fun getSwitchSettings(): List<Int> {
        val list = mutableListOf<Int>()
        with(binding) {
            if (switchEuropean.isChecked) list.add(Categories.European.id)
            if (switchAsian.isChecked) list.add(Categories.Asian.id)
            if (switchPanAsian.isChecked) list.add(Categories.PanAsian.id)
            if (switchEastern.isChecked) list.add(Categories.Eastern.id)
            if (switchAmerican.isChecked) list.add(Categories.American.id)
            if (switchRussian.isChecked) list.add(Categories.Russian.id)
            if (switchMediterranean.isChecked) list.add(Categories.Mediterranean.id)
        }

        return list
    }


    override fun onDestroy() {
        viewModel.setupSwitchSettingsCurrentRecipe(getSwitchSettings())
        super.onDestroy()
    }


    companion object {
        const val KEY_STATE_SWITCH_EUROPEAN = "european"
        const val KEY_STATE_SWITCH_ASIAN = "asian"
        const val KEY_STATE_SWITCH_PAN_ASIAN = "pan_asian"
        const val KEY_STATE_SWITCH_EASTERN = "eastern"
        const val KEY_STATE_SWITCH_AMERICAN = "american"
        const val KEY_STATE_SWITCH_RUSSIAN = "russian"
        const val KEY_STATE_SWITCH_MEDITERRSNEAN = "mediterrsnean"
        const val FILTER_LIST_KEY = "filterListKey"
        const val REQUEST_FILTER_KEY = "requestFilterKey"

    }

}

