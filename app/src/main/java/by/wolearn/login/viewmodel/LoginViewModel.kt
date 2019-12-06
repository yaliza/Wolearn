package by.wolearn.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.R
import by.wolearn.core.view.entities.Resource
import by.wolearn.login.model.repositories.LoginRepository
import kotlinx.coroutines.launch


class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    val loginResult = MutableLiveData<Resource<Unit>>()
    val validationError = MutableLiveData<Int>()

    fun login(login: String, password: String) {
        viewModelScope.launch {
            if (login.isBlank()) {
                validationError.postValue(R.string.login_empty_name)
                return@launch
            }
            if (password.isBlank()) {
                validationError.postValue(R.string.login_empty_password)
                return@launch
            }
            val resp = repository.login(login, password)
            loginResult.postValue(Resource.Loading())
            loginResult.postValue(resp)
        }
    }

}