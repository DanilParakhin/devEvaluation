package co.gotoinc.deveval.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import co.gotoinc.deveval.adapter.PostDataSourceFactory
import co.gotoinc.deveval.adapter.PostPagingAdapter
import co.gotoinc.deveval.model.Post
import co.gotoinc.deveval.network.RetrofitRepository
import co.gotoinc.deveval.util.MainThreadExecutor
import co.gotoinc.deveval.util.PostDiffUtillCallback
import java.util.concurrent.Executors

class MainViewModel : ViewModel() {
    private val repository = RetrofitRepository

    val liveSelectedList = MutableLiveData<List<Post>>().apply { value = listOf() }
    val liveErrorEvent = SingleLiveEvent<Int>()
    val adapter: PostPagingAdapter
    private var pagedList: PagedList<Post>


    init {
        adapter = PostPagingAdapter(PostDiffUtillCallback(), liveSelectedList, ::onItemSelect)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()
        pagedList =
            PagedList.Builder(PostDataSourceFactory(repository, liveErrorEvent).create(), config)
                .setNotifyExecutor(MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
        adapter.submitList(pagedList)
    }

    private fun onItemSelect(isSelected: Boolean, item: Post) {
        liveSelectedList.toggleItem(isSelected, item)
        adapter.notifyItemChanged(pagedList.indexOf(item))
    }

    private fun <T> MutableLiveData<List<T>>.toggleItem(isSelected: Boolean, item: T) {
        value = if (isSelected) value?.plus(item) else value?.minus(item)
    }
}