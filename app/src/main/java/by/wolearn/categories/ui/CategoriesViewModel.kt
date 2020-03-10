package by.wolearn.categories.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.categories.data.CategoriesRepository
import by.wolearn.categories.backend.entities.Category
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.fold
import kotlinx.coroutines.launch


class CategoriesViewModel(
    private val repository: CategoriesRepository
) : ViewModel() {

    val state = MutableLiveData<State>()

    init {
        refresh()
    }

    fun update(category: Category, isSelected: Boolean) {
        if (category.isSelected == isSelected) return
        viewModelScope.launch {
            category.isSelected = isSelected
            // repository.updateCategory(category)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            state.postValue(State.Progress)
            repository.getCategories().fold(
                { state.postValue(State.Success(it)) },
                { handleException(it) }
            )
        }
    }

    private fun handleException(error: Resource.Error<List<Category>>) {
        when (error) {
            is Resource.Error.ApiError -> state.postValue(State.Error(error.exception.message))
            is Resource.Error.UnknownError -> state.postValue(State.UnknownError)
        }
    }

    sealed class State {
        class Success(val categories: List<Category>) : State()
        object Progress : State()
        object UnknownError : State()
        class Error(val message: String) : State()
    }

}