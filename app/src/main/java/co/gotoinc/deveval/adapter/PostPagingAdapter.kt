package eu.gotoinc.kotlinmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import co.gotoinc.deveval.R
import co.gotoinc.deveval.model.Post
import eu.gotoinc.kotlinmvvm.R
import eu.gotoinc.kotlinmvvm.repository.datamodel.response.UserResponse
import eu.gotoinc.kotlinmvvm.viewmodel.UserItemViewModel

class UserPagingAdapter(
    diffCallback: DiffUtil.ItemCallback<>,
    private val onItemClick: () -> Unit
) : PagedListAdapter<Post, ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(UserItemViewModel(getItem(position), onItemClick))

}