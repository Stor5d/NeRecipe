package ru.stor.nerecipe.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.databinding.RecipeListItemBinding
import java.util.*
import kotlin.collections.ArrayList

internal class RecipesAdapter(
    private val interactionListener: RecipeInteractionListener
) : ListAdapter<Recipe, RecipesAdapter.ViewHolder>(DiffCallback), Filterable {

    private var recipeList = mutableListOf<Recipe>()
    private var recipeListFull = mutableListOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeListItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    inner class ViewHolder(
        private val binding: RecipeListItemBinding,
        listener: RecipeInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var recipe: Recipe

//        private val popupMenu by lazy {
//            PopupMenu(itemView.context, binding.menu).apply {
//                inflate(R.menu.option_post)
//                setOnMenuItemClickListener { menuItem ->
//                    when (menuItem.itemId) {
//                        R.id.remove -> {
//                            listener.onRemoveClicked(post)
//                            true
//                        }
//                        R.id.edit -> {
//                            listener.onEditClicked(post)
//                            true
//                        }
//                        else -> false
//                    }
//                }
//            }
//        }

        init {
//            binding.likeButton.setOnClickListener { listener.onLikeClicked(post) }
//            binding.shareButton.setOnClickListener { listener.onShareClicked(post) }
//            binding.menu.setOnClickListener { popupMenu.show() }
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
        recipeListFull.addAll(list)
        recipeList = recipeListFull
        submitList(recipeList)
        //notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' } ?: ""
                if (charString.isEmpty()) recipeList = recipeListFull else {
                    val filteredList = ArrayList<Recipe>()
                    recipeListFull
                        .filter {
                            it.title.lowercase(Locale.getDefault()).contains(charString)

                        }
                        .forEach { filteredList.add(it) }
                    recipeList = filteredList

                }
                return FilterResults().apply { values = recipeList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                recipeList = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Recipe>
                submitList(recipeList)
                //notifyDataSetChanged()
            }
        }
    }

//    override fun getFilter(): Filter {
//        return filter
//    }
//
//
//    private val filter = object : Filter() {
//        override fun performFiltering(constraint: CharSequence): FilterResults {
//            var filteredList: MutableList<Recipe> = ArrayList()
//            Log.e("AAA isEmpty", constraint.isEmpty().toString())
//            Log.e("AAA length", constraint.length.toString())
//            if (constraint.isEmpty() || constraint.length==0) {
//                    filteredList= recipeListFull
//                Log.e("AAA filteredList", filteredList.size.toString())
//            } else {
//                val filterPattern =
//                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
//                    for (item in recipeListFull) {
//                        if (item.title.lowercase(Locale.getDefault()).contains(filterPattern)) {
//                            filteredList.add(item)
//                        }
//                }
//            }
//            val results = FilterResults()
//            Log.e("AAA_filteredList", filteredList.size.toString())
//            results.values = filteredList
//            return results
//        }
//
//        override fun publishResults(constraint: CharSequence, results: FilterResults) {
//            recipeList.clear()
//            recipeList= results.values as MutableList<Recipe>
//           notifyDataSetChanged()
//        }
//    }
//
//    override fun submitList(list: MutableList<Recipe>?) {
//        Log.e("AAA data", "")
//        if (list != null) {
//            recipeListFull= list
//        }
//        super.submitList(list)
//    }
}

private object DiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem == newItem

}