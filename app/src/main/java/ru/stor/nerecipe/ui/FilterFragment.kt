package ru.stor.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import ru.stor.nerecipe.adapter.RecipesAdapter
import ru.stor.nerecipe.databinding.FeedFragmentBinding
import ru.stor.nerecipe.viewModel.RecipeViewModel

class FilterFragment : Fragment() {

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

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ) = FeedFragmentBinding.inflate(
//        layoutInflater, container, false
//    ).also { binding ->
//
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
//
//    }.root

}

