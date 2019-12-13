package by.wolearn.settings.di

import by.wolearn.settings.model.SettingsRepository
import by.wolearn.settings.viewmodel.SettingsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val settingsModule = module {

    single { SettingsRepository(get(), get(), get()) }
    viewModel { SettingsViewModel(get()) }

}