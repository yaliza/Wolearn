package by.wolearn.auth

import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(private val onAuthFailedListener: OnAuthFailedListener) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${AuthPreferences.getJwt()}")
            .build()
        val result = chain.proceed(request)
        if (result.code() == 401) {
            AuthPreferences.clear()
            onAuthFailedListener.onAuthFailed()
        }
        return result
    }

}