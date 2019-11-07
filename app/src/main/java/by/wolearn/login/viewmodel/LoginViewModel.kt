package by.wolearn.login.viewmodel

import androidx.lifecycle.ViewModel
import by.wolearn.login.model.LoginRepository


class LoginViewModel(
    val repository: LoginRepository
) : ViewModel()