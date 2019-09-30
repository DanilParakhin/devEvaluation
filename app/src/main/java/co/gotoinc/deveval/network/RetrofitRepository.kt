package co.gotoinc.deveval.network

import co.gotoinc.deveval.model.ResponseEntity
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitRepository {
    private val retrofitRequests: Requests

    private const val HOSTNAME = "https://hn.algolia.com/api/v1/"

    init {
        val builder = GsonBuilder()
        builder.excludeFieldsWithoutExposeAnnotation()
        builder.setLenient()
        val gson = builder.create()
        val loggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(HOSTNAME)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        retrofitRequests = retrofit.create(Requests::class.java)
    }

    suspend fun getPosts(page: Int): ResponseEntity? {
        try {
            val response = retrofitRequests.getPosts("story", page)
            return response.body()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }
}