package by.wolearn.learning.di

import by.wolearn.learning.model.repositories.LearningRepository
import by.wolearn.categories.ui.CategoriesViewModel
import by.wolearn.learning.viewmodel.LearningViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val learningModule = module {

    single { LearningRepository(get()) }
    viewModel { LearningViewModel(get()) }

}