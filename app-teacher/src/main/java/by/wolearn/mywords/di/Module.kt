package by.wolearn.mywords.di

import by.wolearn.mywords.ui.MyWordsViewModel
import by.wolearn.mywords.backend.MyWordsApi
import by.wolearn.mywords.data.MyWordsRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val myWordsModule = module {

    viewModel { MyWordsViewModel(get()) }
    single { MyWordsRepository(get()) }
    single { MyWordsApi.get(get()) }
}