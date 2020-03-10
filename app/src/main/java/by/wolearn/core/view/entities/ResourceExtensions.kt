package by.wolearn.core.view.entities


fun<T> Resource<T>.fold(
    onSuccess: (T) -> Unit,
    onError: (Resource.Error<T>) -> Unit
) {
    if(this is Resource.Success) {
        onSuccess.invoke(this.data)
    } else if(this is Resource.Error) {
        onError.invoke(this)
    }
}