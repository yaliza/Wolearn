package by.wolearn.categories.backend

import by.wolearn.categories.backend.entities.Category
import by.wolearn.core.configuration.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoriesApi {

    @GET("categories")
    suspend fun getCategories(): List<Category>

    @POST("categories")
    suspend fun setCategories(@Body categories: List<Category>)

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