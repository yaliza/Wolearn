package by.wolearn.login.di

import by.wolearn.login.backend.LoginApi
import by.wolearn.login.data.LoginRepository
import by.wolearn.login.viewmodel.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val loginModule = module {

    viewModel { LoginViewModel(get()) }
    single { LoginRepository(get()) }
    single { LoginApi.get(get()) }
    
}