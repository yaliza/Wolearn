package by.wolearn.settings.backend

import by.wolearn.core.configuration.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

interface SettingsApi {

    @POST("/v1/delete_profile")
    suspend fun deleteProfile(): Unit

    companion object {
        fun get(interceptors: List<Interceptor>): SettingsApi {
            val httpClient = OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }
            return Retrofit.Builder()
                .baseUrl(NetworkConfiguration.BASE_SETTINGS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(SettingsApi::class.java)
        }
    }
}