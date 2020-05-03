package by.wolearn.addword.backend

import by.wolearn.addword.backend.entities.PartOfSpeech
import by.wolearn.core.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface PartOfSpeechApi {

    @GET("pos")
    suspend fun getPartOfSpeeches(): List<PartOfSpeech>

    companion object {
        fun get(interceptors: List<Interceptor>): PartOfSpeechApi {
            val httpClient = OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }
            return Retrofit.Builder()
                .baseUrl(NetworkConfiguration.BASE_PART_OF_SPEECHES)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(PartOfSpeechApi::class.java)
        }
    }

}