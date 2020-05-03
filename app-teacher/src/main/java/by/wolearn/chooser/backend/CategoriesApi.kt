package by.wolearn.chooser.backend

import by.wolearn.chooser.backend.entities.CategoryResponse
import by.wolearn.core.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface CategoriesApi {

    @GET("/v1/teacher/categories")
    suspend fun getCategories() : List<CategoryResponse>

    companion object {
        fun get(interceptors: List<Interceptor>): CategoriesApi {
            val httpClient = OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }
            return Retrofit.Builder()
                .baseUrl(NetworkConfiguration.BASE_CATEGORIES_TEACHERS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(CategoriesApi::class.java)
        }
    }
}