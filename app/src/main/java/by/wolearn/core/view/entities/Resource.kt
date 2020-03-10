package by.wolearn.core.view.entities

import by.wolearn.core.backend.ApiException


sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()

    sealed class Error<T> : Resource<T>() {
        class ApiError<T>(val exception: ApiException) : Error<T>()
        class UnknownError<T> : Error<T>()
    }
}