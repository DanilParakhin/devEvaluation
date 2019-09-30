package co.gotoinc.deveval.adapter

import androidx.paging.DataSource
import co.gotoinc.deveval.adapter.PostPagedDataSource
import co.gotoinc.deveval.model.Post
import co.gotoinc.deveval.network.RetrofitRepository
import co.gotoinc.deveval.viewmodel.SingleLiveEvent

class PostDataSourceFactory(
    private val retrofitRepository: RetrofitRepository,
    private val liveErrorEvent: SingleLiveEvent<Int>
) : DataSource.Factory<Int, Post>() {

    override fun create() = PostPagedDataSource(retrofitRepository, liveErrorEvent)

}