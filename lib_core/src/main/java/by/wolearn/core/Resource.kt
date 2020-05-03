package by.wolearn.core

import java.lang.Exception


sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()

    sealed class Error<T>(val error: Throwable) : Resource<T>() {
        class ApiError<T>(val exception: ApiException) : Error<T>(exception)
        class UnknownError<T>(error: Throwable) : Error<T>(error)
    }
}