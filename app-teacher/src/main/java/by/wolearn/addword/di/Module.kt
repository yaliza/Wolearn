package by.wolearn.addword.di

import by.wolearn.addword.ui.AddWordViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addWordsModule = module {

    viewModel { AddWordViewModel() }
}