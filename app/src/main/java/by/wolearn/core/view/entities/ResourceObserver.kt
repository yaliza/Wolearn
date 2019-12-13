package by.wolearn.core.view.entities

import androidx.lifecycle.Observer


abstract class ResourceObserver<T> : Observer<Resource<T>> {
    override fun onChanged(t: Resource<T>) {
        when (t) {
            is Resource.Success -> onSuccess(t.data)
            is Resource.Error -> onError(t)
            is Resource.Loading -> onLoad()
        }
    }

    abstract fun onSuccess(data: T?): Unit?
    abstract fun onError(error: Resource.Error<T>)
    abstract fun onLoad()
}