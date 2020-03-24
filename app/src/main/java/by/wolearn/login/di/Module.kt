package by.wolearn.login.di

import by.wolearn.auth.LoginApi
import by.wolearn.login.data.LoginRepository
import by.wolearn.login.ui.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val loginModule = module {

    viewModel { LoginViewModel(get()) }
    single { LoginRepository(get()) }
    single { LoginApi.get(get()) }
    
}