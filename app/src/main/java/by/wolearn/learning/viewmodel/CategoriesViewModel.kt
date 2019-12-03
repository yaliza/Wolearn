package by.wolearn.learning.viewmodel

import androidx.lifecycle.*
import by.wolearn.core.view.entities.Resource
import by.wolearn.learning.model.entities.Category
import by.wolearn.learning.model.repositories.CategoriesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CategoriesViewModel(
    private val repository: CategoriesRepository
) : ViewModel() {

    val categories: LiveData<Resource<List<Category>>> = liveData {
        emit(Resource.Loading<List<Category>>())
        delay(1000)
        val result = repository.getCategories()
        emit(result)
    }

    fun update(category: Category, isSelected: Boolean) {
        viewModelScope.launch {
            if (category.isSelected != isSelected) {
                category.isSelected = isSelected
                repository.updateCategory(category)
            }
        }
    }

}