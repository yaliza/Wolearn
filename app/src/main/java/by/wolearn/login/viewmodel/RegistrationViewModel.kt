package by.wolearn.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.R
import by.wolearn.core.view.entities.Resource
import by.wolearn.login.model.entities.NewUser
import by.wolearn.login.model.repositories.RegistrationRepository
import kotlinx.coroutines.launch

class RegistrationViewModel(val repository: RegistrationRepository) : ViewModel() {

    val validation = MutableLiveData<Int>()
    val registration = MutableLiveData<Resource<Unit>>()

    fun register(login: String, password: String, name: String, surname: String) {
        viewModelScope.launch {
            if (!isValid(login, password, name, surname)) return@launch
            registration.postValue(Resource.Loading())
            val resp = repository.register(NewUser(login, password, name, surname))
            registration.postValue(resp)
        }
    }

    private fun isValid(login: String, password: String, name: String, surname: String): Boolean {
        if (login.isBlank()) {
            validation.postValue(R.string.register_empty_login)
            return false
        }
        if (password.isBlank()) {
            validation.postValue(R.string.register_empty_password)
            return false
        }
        if (name.isBlank()) {
            validation.postValue(R.string.register_empty_name)
            return false
        }
        if (surname.isBlank()) {
            validation.postValue(R.string.register_empty_surname)
            return false
        }
        return true
    }
}