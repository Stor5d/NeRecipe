package ru.stor.nerecipe.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.stor.nerecipe.R
import ru.stor.nerecipe.classes.Stage
import ru.stor.nerecipe.databinding.CardStageLayoutBinding

internal class StagesAdapter(
    private val interactionListener: StageInteractionListener
) : ListAdapter<Stage, StagesAdapter.ViewHolder>(DiffCallback2) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardStageLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ViewHolder(
        private val binding: CardStageLayoutBinding,
        listener: StageInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var stage: Stage

        private val popupMenu by lazy {
            PopupMenu(itemView.context, binding.menuStageButton).apply {
                inflate(R.menu.stage_menu)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.addImage -> {
                            Log.e("AAA add", "")
                            true
                        }
                        R.id.remove -> {

                            listener.onRemoveStageClicked(stage)
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
            binding.menuStageButton.setOnClickListener { popupMenu.show() }
//            binding.play.setOnClickListener { listener.onPlayClicked(post) }
//            binding.preView.setOnClickListener { listener.onPlayClicked(post) }
//            binding.avatar.setOnClickListener { listener.onToPost(post) }
//            binding.recipeName.setOnClickListener { listener.onToRecipe(recipe) }
//            binding.authorName.setOnClickListener { listener.onToRecipe(recipe) }
//            binding.category.setOnClickListener { listener.onToRecipe(recipe) }
//            binding.date.setOnClickListener { listener.onToPost(post) }
//            binding.contentEditText.setOnClickListener { listener.onToPost(post) }
        }

        fun bind(stage: Stage, position: Int) {
            this.stage = stage
            with(binding) {
                val positionStage = layoutPosition + 1
                textViewStageContent.text = stage.content + "/" + stage.id + "/" + stage.uriPhoto
                textViewCaptionStage.text = "Шаг $positionStage"
                if (stage.uriPhoto != null) {
                    imageStage.visibility = View.VISIBLE
                    Glide.with(imageStage).load(stage.uriPhoto).override(1000, 1000)
                        .into(imageStage)
                } else {
                    imageStage.visibility = View.GONE
                }

            }
        }


    }
}

private object DiffCallback2 : DiffUtil.ItemCallback<Stage>() {

    override fun areItemsTheSame(oldItem: Stage, newItem: Stage): Boolean =
        oldItem.content == newItem.content

    override fun areContentsTheSame(oldItem: Stage, newItem: Stage): Boolean =
        oldItem == newItem

}