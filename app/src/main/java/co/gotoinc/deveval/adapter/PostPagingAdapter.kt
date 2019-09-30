package co.gotoinc.deveval.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import co.gotoinc.deveval.R
import co.gotoinc.deveval.model.Post
import co.gotoinc.deveval.viewmodel.PostItemViewModel

class PostPagingAdapter(
    diffCallback: DiffUtil.ItemCallback<Post>,
    private val liveSelectedItems: LiveData<List<Post>>,
    private val onItemSwitch: (Boolean, Post) -> Unit
) : PagedListAdapter<Post, ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item =  getItem(position)!!
        val isSelected = liveSelectedItems.value?.contains(item)
        holder.bind(PostItemViewModel(item, isSelected , onItemSwitch))
    }

}