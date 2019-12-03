package by.wolearn.network

import okhttp3.Interceptor
import okhttp3.Response


class WolearnInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter(PARAM_TOKEN, "test").build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}