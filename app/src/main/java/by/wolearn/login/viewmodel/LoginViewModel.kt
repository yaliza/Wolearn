package by.wolearn.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.core.backend.ApiException
import by.wolearn.core.backend.BackendErrors
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.fold
import by.wolearn.login.data.LoginRepository
import kotlinx.coroutines.launch


class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    val state = MutableLiveData<State>()
    val clearErrors = MutableLiveData<Unit>()

    fun login(login: String, password: String) {
        viewModelScope.launch {
            clearErrors.postValue(Unit)
            state.postValue(State.Progress)
            repository.login(login, password).fold(
                { state.postValue(State.Success) },
                { handleError(it) }
            )
        }
    }

    private fun handleError(error: Resource.Error<Unit>) {
        when (error) {
            is Resource.Error.ApiError -> handleApiException(error.exception)
            is Resource.Error.UnknownError -> state.postValue(State.UnknownError)
        }
    }

    private fun handleApiException(apiException: ApiException) {
        when (apiException.code) {
            BackendErrors.LOGIN_ERROR_1 -> state.postValue(State.LoginError(apiException.message))
            BackendErrors.PASSWORD_ERROR_1, BackendErrors.PASSWORD_ERROR_2, BackendErrors.PASSWORD_ERROR_3 ->
                state.postValue(State.PasswordError(apiException.message))
            else -> state.postValue(State.Error(apiException.message))
        }
    }

    sealed class State {
        object Success : State()
        object Progress : State()
        data class Error(val message: String) : State()
        data class LoginError(val message: String) : State()
        data class PasswordError(val message: String) : State()
        object UnknownError : State()
    }
}