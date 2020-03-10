package by.wolearn.login.backend

import by.wolearn.core.configuration.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginApi {

    @POST("/v1/auth/signin")
    suspend fun signIn(@Body params: Map<String, String>): LoginResponse

    companion object {
        fun get(interceptors: List<Interceptor>): LoginApi {
            val httpClient = OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }
            return Retrofit.Builder()
                .baseUrl(NetworkConfiguration.BASE_AUTH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(LoginApi::class.java)
        }
    }
}