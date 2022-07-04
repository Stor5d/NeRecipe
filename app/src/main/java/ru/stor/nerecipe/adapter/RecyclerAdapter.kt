package ru.stor.nerecipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.stor.nerecipe.R
import ru.stor.nerecipe.classes.Recipe
import ru.stor.nerecipe.databinding.CardLayoutBinding
import ru.stor.nerecipe.ui.RecipeCreateFragment

internal class RecyclerAdapter(
    private val interactionListener: RecipeInteractionListener
) : ListAdapter<Recipe, RecyclerAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, interactionListener)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: CardLayoutBinding,
        listener: RecipeInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        var itemTextView: TextView = binding.title
        private lateinit var recipe: Recipe


        private val popupMenu by lazy {
            PopupMenu(itemView.context, binding.menuButton).apply {
                inflate(R.menu.recipe_menu)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.remove -> {
                            listener.onRemoveClicked(recipe.id)
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

            itemTextView.setOnClickListener {
                Toast.makeText(
                    itemTextView.context,
                    "Position $layoutPosition \n $oldPosition \n ${currentList[layoutPosition]} ",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            binding.likeButton.setOnClickListener { listener.onLikeClicked(recipe.id) }
            binding.menuButton.setOnClickListener { popupMenu.show() }
            binding.cardView.setOnClickListener { listener.onToRecipe(recipe) }
            binding.authorName.setOnClickListener { listener.onToRecipe(recipe) }
            binding.title.setOnClickListener { listener.onToRecipe(recipe) }
            binding.categoryTextViewCaption.setOnClickListener { listener.onToRecipe(recipe) }
            binding.categoryTextViewContent.setOnClickListener { listener.onToRecipe(recipe) }
        }

        fun bind(recipe: Recipe) {
            this.recipe = recipe
            with(binding) {
                itemTextView.text = recipe.title
                authorName.text =
                    recipe.author
                categoryTextViewContent.text =
                    RecipeCreateFragment.getCategoriesText(recipe.categories)
                likeButton.isChecked = recipe.liked
            }
        }
    }
}

object DiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem == newItem
}