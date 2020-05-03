package by.wolearn.learningmode.di

import by.wolearn.learningmode.data.LearningModeRepository
import by.wolearn.learningmode.ui.LearningModeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val learningModeModule = module {

    single { LearningModeRepository(get()) }
    viewModel { LearningModeViewModel(get()) }

}
