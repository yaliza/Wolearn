package by.wolearn.learning.viewmodel

import androidx.lifecycle.*
import by.wolearn.learning.model.entities.Category
import by.wolearn.learning.model.repositories.CategoriesRepository


class CategoriesViewModel(
    private val repository: CategoriesRepository
) : ViewModel() {

    val categories: LiveData<List<Category>> = liveData { emit(repository.getCategories()) }

}