package by.wolearn.addword.backend

import by.wolearn.addword.backend.entities.PartOfSpeech
import by.wolearn.addword.backend.entities.WordInsert
import by.wolearn.core.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface WordInsertApi {

    @POST("add/word")
    suspend fun insertWord(@Body word: WordInsert): Unit

    companion object {
        fun get(interceptors: List<Interceptor>): WordInsertApi {
            val httpClient = OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }
            return Retrofit.Builder()
                .baseUrl(NetworkConfiguration.BASE_WORD_INSERT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(WordInsertApi::class.java)
        }
    }
}
