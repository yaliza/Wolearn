package by.wolearn.core

import com.google.gson.Gson
import retrofit2.HttpException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> =
    try {
        Resource.Success(apiCall.invoke())
    } catch (thr: Throwable) {
        when (thr) {
            is HttpException -> {
                val apiException =
                    parseApiException(thr.response()?.errorBody()?.string())
                if (apiException == null) Resource.Error.UnknownError(thr) else Resource.Error.ApiError(apiException)
            }
            else -> Resource.Error.UnknownError(thr)
        }
    }

private fun parseApiException(body: String?): by.wolearn.core.ApiException? {
    return Gson().fromJson(body, by.wolearn.core.ApiException::class.java)
}