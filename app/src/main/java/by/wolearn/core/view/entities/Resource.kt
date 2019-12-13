package by.wolearn.core.view.entities


sealed class Resource<T>(val data: T? = null, val exception: Throwable? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Loading<T> : Resource<T>(null)

    sealed class Error<T> : Resource<T>() {
        class HttpError<T>(val code: Int, val message: String?) : Error<T>()
        class NetworkError<T> : Error<T>()
        class UnknownError<T> : Error<T>()
    }
}

inline fun <F, T> Resource<F>.mapResource(map: (F?) -> T?): Resource<T> =
    when (this) {
        is Resource.Success -> Resource.Success(map(data))
        is Resource.Loading -> Resource.Loading()
        is Resource.Error.HttpError -> Resource.Error.HttpError(code, message)
        is Resource.Error.NetworkError -> Resource.Error.NetworkError()
        is Resource.Error.UnknownError -> Resource.Error.UnknownError()
    }
