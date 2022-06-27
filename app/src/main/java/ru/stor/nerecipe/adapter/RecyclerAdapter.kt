package ru.stor.nerecipe.adapter

import android.icu.text.Transliterator
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.stor.nerecipe.R
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.databinding.CardLayoutBinding
import ru.stor.nerecipe.databinding.EmptyListLayoutBinding
import ru.stor.nerecipe.databinding.RecipeListItemBinding
import java.util.*

internal class RecyclerAdapter(
    private val interactionListener: RecipeInteractionListener
) : ListAdapter<Recipe, RecyclerAdapter.ViewHolder>(DiffCallback) {

    //private lateinit var recipeList: MutableList<Recipe>
    // lateinit var recipeListFull: MutableList<Recipe>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardLayoutBinding.inflate(inflater, parent, false)
//        binding.cardView.setOnClickListener {
//            Toast.makeText(parent.context,"Position",Toast.LENGTH_SHORT).show()
//        }
        return ViewHolder(binding, interactionListener)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

//    fun addData(list: List<Recipe>) {
////        recipeListFull = mutableListOf<Recipe>()
////        recipeListFull.addAll(list)
//        recipeList = list as MutableList<Recipe>
//        submitList(recipeList)
//    }



    inner class ViewHolder(
        private val binding: CardLayoutBinding,
        listener: RecipeInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        var itemTextView: TextView = binding.title
        private lateinit var recipe: Recipe


//        private val popupMenu by lazy {
//            PopupMenu(itemView.context, binding.menu).apply {
//                inflate(R.menu.recipe_menu)
//                setOnMenuItemClickListener { menuItem ->
//                    when (menuItem.itemId) {
//                        R.id.remove -> {
//                            listener.onRemoveClicked(recipe)
//                            true
//                        }
//                        R.id.edit -> {
//                            listener.onEditClicked(recipe)
//                            true
//                        }
//                        else -> false
//                    }
//                }
//            }
//        }

        init {

            itemTextView.setOnClickListener {
                //val position = adapterPosition
                Toast.makeText(
                    itemTextView.context,
                    "Position $layoutPosition \n $oldPosition \n ${currentList[layoutPosition]} ",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            //            binding.likeButton.setOnClickListener { listener.onLikeClicked(post) }
//            binding.shareButton.setOnClickListener { listener.onShareClicked(post) }
            //  binding.menu.setOnClickListener { popupMenu.show() }
//            binding.play.setOnClickListener { listener.onPlayClicked(post) }
//            binding.preView.setOnClickListener { listener.onPlayClicked(post) }
//            binding.avatar.setOnClickListener { listener.onToPost(post) }
//            binding.recipeName.setOnClickListener { listener.onToRecipe(recipe) }
//            binding.authorName.setOnClickListener { listener.onToRecipe(recipe) }
//            binding.category.setOnClickListener { listener.onToRecipe(recipe) }
//            binding.date.setOnClickListener { listener.onToPost(post) }
//            binding.contentEditText.setOnClickListener { listener.onToPost(post) }
        }


        fun bind(recipe: Recipe) {
            this.recipe = recipe
            with(binding) {
                itemTextView.text = recipe.title
//                recipeName.text = recipe.title
//                authorName.text = recipe.author
//                category.text = recipe.id.toString()
            }
        }
    }

//    fun addData(list: List<Recipe>) {
//        recipeListFull = mutableListOf<Recipe>()
//        recipeListFull.addAll(list)
//        recipeList = recipeListFull
//        submitList(recipeList)
//    }

//    override fun submitList(list: MutableList<Recipe>?) {
//        super.submitList(list)
//        notifyDataSetChanged()
//    }

//    override fun getItemCount(): Int {
//        return recipeList.size
//    }



}

//object DiffCallback2 : DiffUtil.ItemCallback<Recipe>() {
//
//    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//
//    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
//        oldItem == newItem
//
//}