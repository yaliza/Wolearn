package by.wolearn.profile.di

import by.wolearn.profile.backend.HistoryApi
import by.wolearn.profile.backend.ProfileApi
import by.wolearn.profile.data.ProfileRepository
import by.wolearn.profile.ui.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val profileModule = module {

    single { ProfileRepository(get(), get()) }
    single { ProfileApi.get(get()) }
    single { HistoryApi.get(get()) }
    viewModel { ProfileViewModel(get()) }

}