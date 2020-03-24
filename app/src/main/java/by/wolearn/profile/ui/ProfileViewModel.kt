package by.wolearn.profile.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.core.Resource
import by.wolearn.profile.backend.entities.HistoryWord
import by.wolearn.profile.backend.entities.Profile
import by.wolearn.profile.data.ProfileRepository
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {

    val state = MutableLiveData<State>()

    init {
        state.value = State.Progress
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val profile = repository.getProfile()
            val history = repository.getHistory()
            if (profile is by.wolearn.core.Resource.Success && history is by.wolearn.core.Resource.Success) {
                state.postValue(State.Data(profile.data, history.data))
            }
        }
    }

    sealed class State {
        class Data(val profile: Profile, val history: List<HistoryWord>) : State()
        class Error(val message: String) : State()
        object Progress : State()
        object UnknownError : State()
    }

}