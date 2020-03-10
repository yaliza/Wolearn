package by.wolearn.profile.backend

import by.wolearn.core.configuration.NetworkConfiguration
import by.wolearn.learning.backend.WordsApi
import by.wolearn.profile.backend.entities.Profile
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ProfileApi {

    @GET("profile")
    suspend fun getProfile() : Profile

    companion object {
        fun get(interceptors: List<Interceptor>): ProfileApi {
            val httpClient = OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }
            return Retrofit.Builder()
                .baseUrl(NetworkConfiguration.BASE_PROFILE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(ProfileApi::class.java)
        }
    }
}