package by.wolearn.registration.ui

import android.mtp.MtpConstants
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.core.backend.ApiException
import by.wolearn.core.backend.BackendErrors
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.fold
import by.wolearn.registration.data.RegistrationRepository
import kotlinx.coroutines.launch


class RegistrationViewModel(
    private val repository: RegistrationRepository
) : ViewModel() {

    val state = MutableLiveData<State>()
    val clearErrors = MutableLiveData<Unit>()

    fun register(name: String, surname: String, login: String, password: String) {
        viewModelScope.launch {
            clearErrors.postValue(Unit)
            state.postValue(State.Progress)
            repository.register(name, surname, login, password).fold(
                { state.postValue(State.Success) },
                { handleException(it) }
            )
        }
    }

    private fun handleException(error: Resource.Error<Unit>) {
        when (error) {
            is Resource.Error.ApiError -> handleApiException(error.exception)
            is Resource.Error.UnknownError -> state.postValue(State.UnknownError)
        }
    }

    private fun handleApiException(apiException: ApiException) {
        when (apiException.code) {
            BackendErrors.REGISTER_NAME_ERROR_1 -> state.postValue(
                State.Error.NameError(
                    apiException.message
                )
            )
            BackendErrors.REGISTER_SURNAME_ERROR_1 -> state.postValue(
                State.Error.SurnameError(
                    apiException.message
                )
            )
            BackendErrors.LOGIN_ERROR_1 -> state.postValue(State.Error.LoginError(apiException.message))
            BackendErrors.PASSWORD_ERROR_1, BackendErrors.PASSWORD_ERROR_2, BackendErrors.PASSWORD_ERROR_3 ->
                state.postValue(State.Error.PasswordError(apiException.message))
            else -> state.postValue(State.Error.CommonError(apiException.message))
        }
    }

    sealed class State {
        object Success : State()
        object Progress : State()
        object UnknownError : State()
        sealed class Error() : State() {
            data class NameError(val message: String) : Error()
            data class SurnameError(val message: String) : Error()
            data class LoginError(val message: String) : Error()
            data class PasswordError(val message: String) : Error()
            data class CommonError(val message: String) : Error()
        }

    }

}