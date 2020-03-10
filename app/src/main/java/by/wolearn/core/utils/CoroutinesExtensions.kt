package by.wolearn.core.utils

import by.wolearn.core.backend.ApiException
import by.wolearn.core.view.entities.Resource
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
                if (apiException == null) Resource.Error.UnknownError() else Resource.Error.ApiError(apiException)
            }
            else -> Resource.Error.UnknownError()
        }
    }

private fun parseApiException(body: String?): ApiException? {
    return Gson().fromJson(body, ApiException::class.java)
}