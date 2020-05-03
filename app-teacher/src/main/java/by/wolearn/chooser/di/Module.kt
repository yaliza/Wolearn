package by.wolearn.chooser.di

import by.wolearn.chooser.backend.CategoriesApi
import by.wolearn.chooser.data.CategoriesRepository
import by.wolearn.chooser.ui.CategoryChooserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val categoryChooserModule = module {

    single { CategoriesApi.get(get()) }
    single { CategoriesRepository(get()) }
    viewModel { CategoryChooserViewModel(get()) }

}
