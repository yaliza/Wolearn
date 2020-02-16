package by.wolearn.categories.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoriesApi {

    @GET("v1/categories")
    suspend fun getCategories(): List<Category>

    @POST("v1/categories")
    suspend fun setCategories(@Body categories: List<Category>)

}