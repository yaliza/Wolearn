package by.wolearn.network

import by.wolearn.core.model.TokenStorage
import okhttp3.Interceptor
import okhttp3.Response


class WolearnInterceptor(private val tokenStorage: TokenStorage) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val headers =
            request.headers().newBuilder().add("Authorization", "Bearer ${tokenStorage.get()?.token}")
                .build()
        request = request.newBuilder().headers(headers).build()
        return chain.proceed(request)
    }

}