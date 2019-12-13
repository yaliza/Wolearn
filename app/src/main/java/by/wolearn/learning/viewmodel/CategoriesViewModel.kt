package by.wolearn.learning.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import by.wolearn.core.view.entities.Resource
import by.wolearn.learning.model.entities.Category
import by.wolearn.learning.model.repositories.CategoriesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CategoriesViewModel(
    private val repository: CategoriesRepository
) : ViewModel() {

    val categories: LiveData<Resource<List<Category>>> = liveData {
        repository.getCategories().collect { emit(it) }
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