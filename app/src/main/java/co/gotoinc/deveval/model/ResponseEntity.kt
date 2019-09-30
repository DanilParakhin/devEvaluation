package co.gotoinc.deveval.model

import com.google.gson.annotations.SerializedName

data class ResponseEntity(
    val hits: List<Post>,
    val nbHits: Long,
    val page: Int,
    val nbPages: Int,
    val hitsPerPages: Int
){
    fun hasNext() = page < nbPages
    fun getNextPage() = if (hasNext()) page + 1 else null
}