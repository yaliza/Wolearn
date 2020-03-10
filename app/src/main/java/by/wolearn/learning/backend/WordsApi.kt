package by.wolearn.learning.backend

import by.wolearn.core.configuration.NetworkConfiguration
import by.wolearn.learning.backend.entities.Word
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface WordsApi {

    @POST("word")
    suspend fun saveWord(@Query("wordId") wordId: Long, @Query("isMemorized") isMemorized: Boolean)

    @GET("words")
    suspend fun getWords(): List<Word>

    companion object {
        fun get(interceptors: List<Interceptor>): WordsApi {
            val httpClient = OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }
            return Retrofit.Builder()
                .baseUrl(NetworkConfiguration.BASE_WORDS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(WordsApi::class.java)
        }
    }

}