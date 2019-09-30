package co.gotoinc.deveval.adapter

import android.util.Log
import androidx.paging.PageKeyedDataSource
import co.gotoinc.deveval.R
import co.gotoinc.deveval.model.Post
import co.gotoinc.deveval.network.RetrofitRepository
import co.gotoinc.deveval.viewmodel.SingleLiveEvent
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostPagedDataSource(
    private val retrofit: RetrofitRepository,
    private val liveErrorEvent: SingleLiveEvent<Int>
) :
    PageKeyedDataSource<Int, Post>() {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Post>
    ) {
        GlobalScope.launch {
            val response = retrofit.getPosts(1)
            if (response != null) {
                callback.onResult(response.hits.orEmpty(), null, response.page + 1)
            } else {
                liveErrorEvent.setValue(R.string.error_message)
            }


        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {
        GlobalScope.launch {
            val response = retrofit.getPosts(params.key)
            if (response != null) {
                callback.onResult(response.hits.orEmpty(), response.getNextPage())
            } else {
                liveErrorEvent.setValue(R.string.error_message)
            }

        }
    }


}