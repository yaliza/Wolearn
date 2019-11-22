package by.wolearn.profile.viewmodel

import androidx.lifecycle.ViewModel
import by.wolearn.profile.model.ProfileRepository


class ProfileViewModel(
    val repository: ProfileRepository
) : ViewModel()