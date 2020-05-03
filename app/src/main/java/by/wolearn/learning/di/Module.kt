package by.wolearn.learning.di

import by.wolearn.learning.backend.WordsApi
import by.wolearn.learning.data.LearningRepository
import by.wolearn.learning.ui.LearningViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val learningModule = module {

    single { LearningRepository(get(), get()) }
    single { WordsApi.get(get()) }
    viewModel { LearningViewModel(get()) }

}