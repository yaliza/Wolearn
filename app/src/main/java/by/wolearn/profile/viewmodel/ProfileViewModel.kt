package by.wolearn.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import by.wolearn.profile.model.ProfileRepository


class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {

    val profile = liveData {
        emit(repository.getProfile())
    }

    val history = liveData {
        emit(repository.getHistory())
    }

}