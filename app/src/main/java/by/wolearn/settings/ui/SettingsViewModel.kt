package by.wolearn.settings.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.core.fold
import by.wolearn.settings.data.SettingsRepository
import kotlinx.coroutines.launch


class SettingsViewModel(
    private val repository: SettingsRepository
) : ViewModel() {

    val state = MutableLiveData<State>()

    fun logout() {
        viewModelScope.launch {
            state.postValue(State.Progress)
            repository.logout().fold(
                { state.postValue(State.Logout) },
                { }
            )
        }
    }

    fun deleteProfile() {
        viewModelScope.launch {
            state.postValue(State.Progress)
            repository.deleteProfile().fold(
                { state.postValue(State.Logout) },
                { }
            )
        }
    }

    sealed class State {
        object Logout : State()
        object Progress : State()
    }

}