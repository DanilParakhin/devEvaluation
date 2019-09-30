package co.gotoinc.deveval.util

import androidx.recyclerview.widget.DiffUtil
import co.gotoinc.deveval.model.Post

class PostDiffUtillCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.title.equals(newItem.title, false)
                && oldItem.createdAt == newItem.createdAt

}