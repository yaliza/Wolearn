package by.wolearn.categories.ui

import android.util.Log
import androidx.lifecycle.*
import by.wolearn.core.view.entities.Resource
import by.wolearn.categories.data.Category
import by.wolearn.categories.data.CategoriesRepository
import by.wolearn.core.view.entities.fold
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CategoriesViewModel(
    private val repository: CategoriesRepository
) : ViewModel() {

    val state = MutableLiveData<State>()

    init {
        viewModelScope.launch {
            repository.getCategories().fold(
                { categories: List<Category>? ->
                    categories?.let { state.postValue(State.Success(categories)) }
                },
                {
                    Log.d("LOOOG", "error ${it.exception}")
                }
            )
        }
    }

    fun update(category: Category, isSelected: Boolean) {
        viewModelScope.launch {
            if (category.isSelected != isSelected) {
                category.isSelected = isSelected
                repository.updateCategory(category)
            }
        }
    }

    sealed class State {
        class Success(val categories: List<Category>): State()
        object Progress : State()
        object UnknownError : State()
    }

}