package by.wolearn.categories.di

import by.wolearn.categories.backend.CategoriesApi
import by.wolearn.categories.data.CategoriesRepository
import by.wolearn.categories.ui.CategoriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val categoriesModule = module {

    single { CategoriesApi.get(get()) }
    single { CategoriesRepository(get()) }
    viewModel { CategoriesViewModel(get()) }

}