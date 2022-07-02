package ru.stor.nerecipe.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import ru.stor.nerecipe.R
import ru.stor.nerecipe.adapter.RecyclerAdapter
import ru.stor.nerecipe.adapter.StagesAdapter
import ru.stor.nerecipe.classes.Categories
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.classes.Stage
import ru.stor.nerecipe.databinding.RecipeCreateFragmentBinding
import ru.stor.nerecipe.viewModel.RecipeViewModel


class RecipeCreateFragment : Fragment() {
    private val viewModel by activityViewModels<RecipeViewModel>()

    //private val args by navArgs<RecipeFragmentArgs>()
    private var filterList = arrayListOf<Int>()
    lateinit var binding: RecipeCreateFragmentBinding
    private var currentRecipe: Recipe? = null
    private val stages = mutableListOf<Stage>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //   val navController = findNavController(R.id.)
//        bNav.setupWithNavController(navController)

//        val navController =
//            ((childFragmentManager.findFragmentById(R.id.mainContainerView)) as NavHostFragment).navController
//        NavigationUI.
//        NavigationUI.setupWithNavController(bNav, navController)

        //  val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // val navController = findNavController()

    }

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

//        viewModel.getStages().observe(viewLifecycleOwner) { stages ->
//            if (stages != null) {
//
//            }
//        }


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

    private fun bind(recipe: Recipe?) {
        if (recipe != null) {
            binding.titleEditText.setText(recipe.title)
            binding.categoryTextViewContent.text = getCategoriesText(recipe.categories)
        }
    }

    fun createStage() {
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