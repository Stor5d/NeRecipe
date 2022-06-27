package ru.stor.nerecipe.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.stor.nerecipe.R
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.databinding.EmptyListLayoutBinding
import ru.stor.nerecipe.databinding.RecipeListItemBinding
import java.util.*

internal class RecipesAdapter(
    private val interactionListener: RecipeInteractionListener
) : ListAdapter<Recipe, RecipesAdapter.ViewHolder>(DiffCallback), Filterable {

    lateinit var recipeList: MutableList<Recipe> //= mutableListOf<Recipe>()
    lateinit var recipeListFull: MutableList<Recipe>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, interactionListener)
    }

//    override fun getItemViewType(position: Int): Int {
//        if (recipeList.isEmpty()) {
//            return VIEW_TYPE_EMPTY_LIST_PLACEHOLDER;
//        } else {
//            return VIEW_TYPE_OBJECT_VIEW;
//        }
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: RecipeListItemBinding,
        listener: RecipeInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var recipe: Recipe

        private val popupMenu by lazy {
            PopupMenu(itemView.context, binding.menu).apply {
                inflate(R.menu.recipe_menu)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.remove -> {
                            listener.onRemoveClicked(recipe)
                            true
                        }
                        R.id.edit -> {
                            listener.onEditClicked(recipe)
                            true
                        }
                        else -> false
                    }
                }
            }
        }

        init {
//            binding.likeButton.setOnClickListener { listener.onLikeClicked(post) }
//            binding.shareButton.setOnClickListener { listener.onShareClicked(post) }
            binding.menu.setOnClickListener { popupMenu.show() }
//            binding.play.setOnClickListener { listener.onPlayClicked(post) }
//            binding.preView.setOnClickListener { listener.onPlayClicked(post) }
//            binding.avatar.setOnClickListener { listener.onToPost(post) }
            binding.recipeName.setOnClickListener { listener.onToRecipe(recipe) }
            binding.authorName.setOnClickListener { listener.onToRecipe(recipe) }
            binding.category.setOnClickListener { listener.onToRecipe(recipe) }
//            binding.date.setOnClickListener { listener.onToPost(post) }
//            binding.contentEditText.setOnClickListener { listener.onToPost(post) }
        }

        fun bind(recipe: Recipe) {
            this.recipe = recipe
            with(binding) {
                recipeName.text = recipe.title
                authorName.text = recipe.author
                category.text = recipe.id.toString()
            }
        }
    }

    fun addData(list: List<Recipe>) {
        recipeListFull = mutableListOf<Recipe>()
        recipeListFull.addAll(list)
        recipeList = recipeListFull
        submitList(recipeList)
    }

    override fun submitList(list: MutableList<Recipe>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' } ?: ""
                recipeList = if (charString.isEmpty()) recipeListFull else {
                    recipeListFull.filter { recipe ->
                        recipe.title.lowercase(Locale.getDefault()).contains(charString)
                    }.toMutableList()
                }

//                if (charString.isEmpty()) recipeList = recipeListFull else {
//
//                    val filteredList = ArrayList<Recipe>()
//
//                    recipeListFull
//                        .filter {
//                            it.title.lowercase(Locale.getDefault()).contains(charString)
//
//                        }
//                        .forEach { filteredList.add(it) }
//
//                    recipeList = filteredList
//
//                }
                return FilterResults().apply { values = recipeList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                recipeList = if (results?.values == null)
//                    ArrayList()
//                else
//                    results.values as ArrayList<Recipe>
                submitList(recipeList)
            }
        }
    }


//    inner class ViewHolder2(
//        private val binding: EmptyListLayoutBinding,
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//    }

    companion object {
        const val VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0
        const val VIEW_TYPE_OBJECT_VIEW = 1
    }

}

private object DiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem == newItem

}