package by.wolearn.profile.backend

import by.wolearn.core.configuration.NetworkConfiguration
import by.wolearn.profile.backend.entities.HistoryWord
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface HistoryApi {

    @GET("history")
    suspend fun getHistory(): List<HistoryWord>

    companion object {
        fun get(interceptors: List<Interceptor>): HistoryApi {
            val httpClient = OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }
            return Retrofit.Builder()
                .baseUrl(NetworkConfiguration.BASE_HISTORY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(HistoryApi::class.java)
        }
    }
}