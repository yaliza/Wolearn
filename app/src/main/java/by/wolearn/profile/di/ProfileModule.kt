package by.wolearn.profile.di

import by.wolearn.profile.model.ProfileRepository
import by.wolearn.profile.viewmodel.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val profileModule = module {

    single { ProfileRepository(get()) }
    viewModel { ProfileViewModel(get()) }

}