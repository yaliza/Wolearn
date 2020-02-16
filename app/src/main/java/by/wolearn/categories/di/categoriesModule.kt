package by.wolearn.categories.di

import by.wolearn.categories.data.CategoriesApi
import by.wolearn.categories.data.CategoriesRepository
import by.wolearn.categories.ui.CategoriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val categoriesModule = module {

    single { get<Retrofit>().create(CategoriesApi::class.java) }
    single { CategoriesRepository(get()) }
    viewModel { CategoriesViewModel(get()) }

}