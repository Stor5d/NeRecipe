package ru.stor.nerecipe.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import ru.stor.nerecipe.R
import ru.stor.nerecipe.adapter.RecipesAdapter
import ru.stor.nerecipe.adapter.StagesAdapter
import ru.stor.nerecipe.classes.Categories
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.classes.Stage
import ru.stor.nerecipe.databinding.RecipeCreateFragmentBinding
import ru.stor.nerecipe.viewModel.RecipeViewModel
import ru.stor.nerecipe.viewModel.StageViewModel


class RecipeCreateFragment : Fragment(){
    private val viewModel by activityViewModels<RecipeViewModel>()

    //private val args by navArgs<RecipeFragmentArgs>()
    private var filterList = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

//    override fun onBackPressed() {
//        Log.e("AAA","BackPressed")
//        viewModel.currentRecipe.value = null
//    }

    private fun onSaveButtonClicked(binding: RecipeCreateFragmentBinding) {
        val text = binding.titleEditText.text
        if (!text.isNullOrBlank()) {
            val resultBundle = Bundle(2)
            resultBundle.putString(TITLE_KEY, text.toString())
            resultBundle.putIntegerArrayList(CATEGORIES_KEY, filterList)
            setFragmentResult(REQUEST_KEY, resultBundle)
        }
        val direction = RecipeCreateFragmentDirections.actionRecipeCreateFragmentToFeedFragment()
        findNavController().navigate(direction)
    }

    private fun onCancelButtonClicked() {
        val direction = RecipeCreateFragmentDirections.actionRecipeCreateFragmentToFeedFragment()
        findNavController().navigate(direction)
    }


    private fun setFilter() {
        val direction = RecipeCreateFragmentDirections.actionRecipeCreateFragmentToFilterFragment()
        findNavController().navigate(direction)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = RecipeCreateFragmentBinding.inflate(
        layoutInflater, container, false
    ).also { binding ->


        binding.buttonSave.setOnClickListener { onSaveButtonClicked(binding) }
        binding.buttonCancel.setOnClickListener { onCancelButtonClicked() }
        binding.categoryViewStart.setOnClickListener { setFilter() }
        binding.categoryTextViewCaption.setOnClickListener { setFilter() }
        binding.categoryTextViewContent.setOnClickListener { setFilter() }


        setFragmentResultListener(
            requestKey = FilterFragment.REQUEST_FILTER_KEY
        ) { requestKey, bundle ->
            if (requestKey != FilterFragment.REQUEST_FILTER_KEY) return@setFragmentResultListener
            filterList = bundle.getIntegerArrayList(FilterFragment.FILTER_LIST_KEY)
                ?: return@setFragmentResultListener
            var categoryText = ""
            filterList.forEach {
                if (Categories.European.id == it) categoryText += Categories.European.key + "\n"
                if (Categories.Asian.id == it) categoryText += Categories.Asian.key + "\n"
                if (Categories.PanAsian.id == it) categoryText += Categories.PanAsian.key + "\n"
                if (Categories.Eastern.id == it) categoryText += Categories.Eastern.key + "\n"
                if (Categories.American.id == it) categoryText += Categories.American.key + "\n"
                if (Categories.Russian.id == it) categoryText += Categories.Russian.key + "\n"
                if (Categories.Mediterranean.id == it) categoryText += Categories.Mediterranean.key + "\n"
            }
            binding.categoryTextViewContent.text = categoryText
        }


        val editText = binding.titleEditText
        val textView = binding.titleTextView

        editText.addTextChangedListener {
            if (editText.text.isEmpty()) {
                textView.visibility = View.GONE
                editText.textSize = 32F
            } else {
                textView.visibility = View.VISIBLE
                editText.textSize = 20F
            }
        }

        val recipe: Recipe? = viewModel.currentRecipe.value
        if (recipe != null) {
            editText.setText(recipe.title)
        }





//        val adapter = StagesAdapter(viewModelStage)
//        binding.stageRecyclerView.adapter = adapter
//        viewModel.data.observe(viewLifecycleOwner) { stages ->
//            adapter.submitList(stages)
//        }

//        val button = binding.stageAddButton
//        val allEds = ArrayList<View>()
//        val constraint = binding.constraint
//        var viewLastId = binding.stageTextView.id
//        button.setOnClickListener(View.OnClickListener {
//            counter++
//            val view: View = layoutInflater.inflate(R.layout.custom_edit_text_layout, null)
//            //val myEditText = view.findViewById<View>(R.id.editText) as EditText
//            //myEditText.setText("Номер $counter")
//
//            val optionsButton: ImageView = view.findViewById<View>(R.id.options) as ImageView
//            val popupMenu by lazy {
//                PopupMenu(context, optionsButton).apply {
//                    inflate(R.menu.stage_menu)
//                    setOnMenuItemClickListener { menuItem ->
//                        when (menuItem.itemId) {
//                            R.id.remove -> {
//                                constraint.removeView(view)
//                                true
//                            }
//                            R.id.up -> {
//
//                                true
//                            }
//                            R.id.down -> {
//
//                                true
//                            }
//                            else -> false
//                        }
//                    }
//                }
//            }
//
//
//
//
//
//            optionsButton.setOnClickListener {
//                popupMenu.show()
//                //  try {
//                //получаем родительский view и удаляем его
//                // (view.parent as LinearLayout).removeView(view)
//                //удаляем эту же запись из массива что бы не оставалось мертвых записей
//                //allEds.remove(view)
//                //  } catch (ex: IndexOutOfBoundsException) {
//                //      ex.printStackTrace()
//            }
//
//
////            val viewStart: ImageView = view.findViewById<View>(R.id.viewStart) as ImageView
////           viewStart.setOnClickListener{
////
////           }
////
////
////
//            //val text = view.findViewById<View>(ru.stor.nerecipe.R.id.editText) as EditText
//            // text.setText("Some text$counter")
//            //добавляем все что создаем в массив
//            allEds.add(view)
//            //добавляем елементы в linearlayout
//
//            val stageTextView = binding.stageTextView
//
//            val set = ConstraintSet()
//            view.id = View.generateViewId()
//            constraint.addView(view)
//            set.clone(constraint)
//            set.connect(view.id, ConstraintSet.TOP, viewLastId, ConstraintSet.BOTTOM, 18)
//            set.connect(
//                view.id,
//                ConstraintSet.START,
//                ConstraintSet.PARENT_ID,
//                ConstraintSet.START,
//                0
//            )
//            set.connect(view.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
//            set.constrainWidth(view.id, ConstraintSet.MATCH_CONSTRAINT);
//            set.applyTo(constraint)
//            viewLastId = view.id
//
//
////            val layout = findViewById(R.id.mainConstraint) as ConstraintLayout
////            val set = ConstraintSet()
////
////            val view = ImageView(this)
////            view.id = View.generateViewId()
////            layout.addView(view, 0)
////            set.clone(layout)
////            set.connect(view.id, ConstraintSet.TOP, layout.id, ConstraintSet.TOP, 60)
////            set.applyTo(layout)
//
//
//        })
//        //set.clone(constraint)
//
////        viewModel.data.observe(viewLifecycleOwner) { posts ->
////            posts.firstOrNull { post ->
////                post.id == args.postId
////            }?.let { bind(it, binding) }
////
////            val popupMenu by lazy {
////                PopupMenu(context, binding.postItem.menu).apply {
////                    inflate(R.menu.option_post)
////                    setOnMenuItemClickListener { menuItem ->
////                        when (menuItem.itemId) {
////                            R.id.remove -> {
////                                viewModel.onRemoveClickedPost()
////                                val direction =
////                                    RecipeFragmentDirections.actionPostFragmentToFeedFragment()
////                                findNavController().navigate(direction)
////                                true
////                            }
////                            R.id.edit -> {
////                                viewModel.onEditClickedPost()
////                                true
////                            }
////                            else -> false
////                        }
////                    }
////                }
////            }
////
////            with(binding.postItem) {
////                likeButton.setOnClickListener { viewModel.onLikeClickedPost() }
////                shareButton.setOnClickListener { viewModel.onShareClickedPost() }
////                menu.setOnClickListener { popupMenu.show() }
////                play.setOnClickListener { viewModel.onPlayClickedPost() }
////
////            }
////        }


    }.root

    companion object {
        const val TITLE_KEY = "titleKey"
        const val CATEGORIES_KEY = "categoriesKey"
        const val REQUEST_KEY = "requestKey"

    }

}