package by.wolearn.core


sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()

    sealed class Error<T> : Resource<T>() {
        class ApiError<T>(val exception: by.wolearn.core.ApiException) : Error<T>()
        class UnknownError<T> : Error<T>()
    }
}