package by.wolearn.core


fun<T> Resource<T>.fold(
    onSuccess: (T) -> Unit,
    onError: (by.wolearn.core.Resource.Error<T>) -> Unit
) {
    if(this is by.wolearn.core.Resource.Success) {
        onSuccess.invoke(this.data)
    } else if(this is by.wolearn.core.Resource.Error) {
        onError.invoke(this)
    }
}