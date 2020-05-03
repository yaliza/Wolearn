package by.wolearn.mywords.backend

import by.wolearn.core.NetworkConfiguration
import by.wolearn.core.Word
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface MyWordsApi {

    @GET("mywords")
    suspend fun getWords(): List<Word>

    companion object {
        fun get(interceptors: List<Interceptor>): MyWordsApi {
            val httpClient = OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }
            return Retrofit.Builder()
                .baseUrl(NetworkConfiguration.BASE_HISTORY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(MyWordsApi::class.java)
        }
    }

}