package by.wolearn.network

import by.wolearn.learning.model.entities.Category
import by.wolearn.learning.model.entities.Word
import retrofit2.http.GET

interface WolearnApi {

    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("words")
    suspend fun getWords(): List<Word>
}