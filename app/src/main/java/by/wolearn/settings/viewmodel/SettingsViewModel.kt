package by.wolearn.settings.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.core.view.entities.Resource
import by.wolearn.settings.model.SettingsRepository
import kotlinx.coroutines.launch


class SettingsViewModel(
    private val repository: SettingsRepository
) : ViewModel() {

    val deleteAccount = MutableLiveData<Resource<Unit>>()
    val statReset = MutableLiveData<Resource<Unit>>()
    val logout = MutableLiveData<Unit>()

    fun resetStatistics() {
        viewModelScope.launch {
            statReset.postValue(Resource.Loading())
            statReset.postValue(repository.resetStat())
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
            logout.postValue(Unit)
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            deleteAccount.postValue(Resource.Loading())
            val res = repository.deleteAccount()
            deleteAccount.postValue(res)
        }
    }
}