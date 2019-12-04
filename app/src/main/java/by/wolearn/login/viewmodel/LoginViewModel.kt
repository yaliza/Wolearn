package by.wolearn.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.mapResource
import by.wolearn.login.model.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    val loginResult = MutableLiveData<Resource<Unit>>()

    fun login(login: String, password: String) {
        viewModelScope.launch {
            if (login.isBlank()) return@launch
            if (password.isBlank()) return@launch
            val resp = repository.login(login, password)
            loginResult.postValue(resp.mapResource { Unit })
        }
    }

}