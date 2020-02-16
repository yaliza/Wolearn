package by.wolearn.network

import android.util.Log
import by.wolearn.core.view.entities.Resource
import retrofit2.HttpException
import java.io.IOException


suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> =
    try {
        Resource.Success(apiCall.invoke())
    } catch (thr: Throwable) {
        Log.d("LOOOG", "error", thr)
        when (thr) {
            is IOException -> Resource.Error.NetworkError()
            is HttpException -> Resource.Error.HttpError(thr.code(), thr.message)
            else -> Resource.Error.UnknownError()
        }
    }