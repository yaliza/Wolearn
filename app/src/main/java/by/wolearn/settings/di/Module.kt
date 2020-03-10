package by.wolearn.settings.di

import by.wolearn.settings.backend.SettingsApi
import by.wolearn.settings.data.SettingsRepository
import by.wolearn.settings.ui.SettingsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val settingsModule = module {

    single { SettingsApi.get(get()) }
    single { SettingsRepository(get()) }
    viewModel { SettingsViewModel(get()) }

}