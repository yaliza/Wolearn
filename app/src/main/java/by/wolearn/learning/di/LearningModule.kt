package by.wolearn.learning.di

import by.wolearn.learning.model.LearningRepository
import by.wolearn.learning.viewmodel.CategoriesViewModel
import by.wolearn.learning.viewmodel.LearningViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val learningModule = module {

    single { LearningRepository() }
    viewModel { CategoriesViewModel(get()) }
    viewModel { LearningViewModel(get()) }

}