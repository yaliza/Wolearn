package by.wolearn.learning.di

import by.wolearn.learning.model.repositories.CategoriesRepository
import by.wolearn.learning.model.repositories.LearningRepository
import by.wolearn.learning.viewmodel.CategoriesViewModel
import by.wolearn.learning.viewmodel.LearningViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val learningModule = module {

    single { LearningRepository(get()) }
    single { CategoriesRepository(get(), get()) }
    viewModel { CategoriesViewModel(get()) }
    viewModel { LearningViewModel(get()) }

}