package co.gotoinc.deveval.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(
    @SerializedName("created_at_i")
    val createdAt: Long,
    val author: String,
    val title: String
) {
    fun getCreatedAtDate() = Date(createdAt * 1000)
}
