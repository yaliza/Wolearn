package by.wolearn.login.di

import by.wolearn.login.model.RegistrationRepository
import by.wolearn.login.viewmodel.RegistrationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registrationModule = module {

    single { RegistrationRepository() }
    viewModel { RegistrationViewModel(get()) }

}