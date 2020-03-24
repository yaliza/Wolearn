package by.wolearn.categories.backend

import by.wolearn.categories.backend.entities.Category
import by.wolearn.core.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface CategoriesApi {

    @GET("categories")
    suspend fun getCategories(): List<Category>

    @POST("category")
    suspend fun setCategory(@Query("categoryId") categoryId: Int, @Query("isSelected") isSelected: Boolean)

    companion object {

        fun get(interceptors: List<Interceptor>): CategoriesApi {
            val httpClient = OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }
            return Retrofit.Builder()
                .baseUrl(NetworkConfiguration.BASE_CATEGORIES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(CategoriesApi::class.java)
        }

    }
}