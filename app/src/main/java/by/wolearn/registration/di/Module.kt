package by.wolearn.registration.di

import by.wolearn.registration.backend.RegistrationApi
import by.wolearn.registration.backend.RegistrationResponse
import by.wolearn.registration.data.RegistrationRepository
import by.wolearn.registration.ui.RegistrationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registrationModule = module {

    viewModel { RegistrationViewModel(get()) }
    single { RegistrationRepository(get()) }
    single { RegistrationApi.get(get()) }

}