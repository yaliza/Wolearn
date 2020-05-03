package by.wolearn.addword.di

import by.wolearn.addword.backend.PartOfSpeechApi
import by.wolearn.addword.backend.WordInsertApi
import by.wolearn.addword.data.AddWordRepository
import by.wolearn.addword.ui.AddWordUiMapper
import by.wolearn.addword.ui.AddWordViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addWordsModule = module {

    single { PartOfSpeechApi.get(get()) }
    single { WordInsertApi.get(get()) }
    single { AddWordRepository(get(), get()) }
    viewModel { AddWordViewModel(get(), get()) }
    factory { AddWordUiMapper() }

}