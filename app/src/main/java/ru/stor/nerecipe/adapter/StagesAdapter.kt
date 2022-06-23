package ru.stor.nerecipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.stor.nerecipe.classes.Stage
import ru.stor.nerecipe.databinding.CustomEditTextLayoutBinding

internal class StagesAdapter(
    private val interactionListener: StageInteractionListener
) : ListAdapter<Stage, StagesAdapter.ViewHolder>(DiffCallback2) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CustomEditTextLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: CustomEditTextLayoutBinding,
        listener: StageInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var stage: Stage

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
//            binding.recipeName.setOnClickListener { listener.onToRecipe(recipe) }
//            binding.authorName.setOnClickListener { listener.onToRecipe(recipe) }
//            binding.category.setOnClickListener { listener.onToRecipe(recipe) }
//            binding.date.setOnClickListener { listener.onToPost(post) }
//            binding.contentEditText.setOnClickListener { listener.onToPost(post) }
        }

        fun bind(stage: Stage) {
            this.stage=stage
            with(binding) {
//                recipeName.text=recipe.title
//                authorName.text = recipe.author
//                category.text=recipe.id.toString()
            }
        }


    }
}

private object DiffCallback2 : DiffUtil.ItemCallback<Stage>() {

    override fun areItemsTheSame(oldItem: Stage, newItem: Stage): Boolean =
        oldItem.content == newItem.content

    override fun areContentsTheSame(oldItem:Stage, newItem: Stage): Boolean =
        oldItem == newItem

}