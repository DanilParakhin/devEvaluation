package co.gotoinc.deveval.network

import co.gotoinc.deveval.model.ResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Requests {
    @GET("search_by_date")
    suspend fun getPosts(@Query("tags") tags: String, @Query("page") page: Int): Response<ResponseEntity>
}