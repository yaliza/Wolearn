package by.wolearn.registration.backend

import by.wolearn.core.configuration.NetworkConfiguration
import by.wolearn.login.backend.LoginResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface RegistrationApi {

    @POST("/v1/auth/signup")
    suspend fun signUp(@Body params: Map<String, String>): RegistrationResponse

    companion object {
        fun get(interceptors: List<Interceptor>): RegistrationApi {
            val httpClient = OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }
            return Retrofit.Builder()
                .baseUrl(NetworkConfiguration.BASE_AUTH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(RegistrationApi::class.java)
        }
    }
}