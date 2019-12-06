package by.wolearn.login.di

import by.wolearn.login.model.LoginRepository
import by.wolearn.login.viewmodel.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val loginModule = module {

    single { LoginRepository(get(), get()) }
    viewModel { LoginViewModel(get()) }

}