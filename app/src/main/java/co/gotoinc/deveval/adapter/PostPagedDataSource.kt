package eu.gotoinc.kotlinmvvm.adapter

import androidx.paging.PageKeyedDataSource
import co.gotoinc.deveval.R
import co.gotoinc.deveval.model.Post
import co.gotoinc.deveval.model.ResponseEntity
import co.gotoinc.deveval.network.RetrofitRepository
import co.gotoinc.deveval.viewmodel.SingleLiveEvent
import eu.gotoinc.kotlinmvvm.R
import eu.gotoinc.kotlinmvvm.repository.ApiContract
import eu.gotoinc.kotlinmvvm.repository.datamodel.response.UserResponse
import eu.gotoinc.kotlinmvvm.util.architecture.SingleLiveEvent
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class UserPagedDataSource(private val retrofit: RetrofitRepository, private val liveErrorEvent: SingleLiveEvent<Int>) :
    PageKeyedDataSource<Int, Post>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Post>) {
        GlobalScope.launch {
            val response = retrofit.getPosts(0)
            withContext(Main){
                if (response != null){
                    callback.onResult(response.hints, null, response.page+1)
                } else {
                    liveErrorEvent.setValue(R.string.error_message)
                }
            }

        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {
        GlobalScope.launch {
            val response = retrofit.getPosts(0)
            withContext(Main){
                if (response != null){
                    callback.onResult(response.hints, null, response.page+1)
                } else {
                    liveErrorEvent.setValue(R.string.error_message)
                }
            }

        }



        api.getUsersList(params.key)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { listPaginationResponse ->
                    val hasNext = listPaginationResponse.totalPages > listPaginationResponse.page
                    callback.onResult(
                        listPaginationResponse.data,
                        if (hasNext) listPaginationResponse.page + 1 else null
                    )
                }, { throwable -> liveErrorEvent.setValue(R.string.error_message) }
            )
    }


}