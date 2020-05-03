package by.wolearn.chooser.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.chooser.data.CategoriesRepository
import by.wolearn.chooser.ui.entities.CategoryItem
import by.wolearn.core.fold
import kotlinx.coroutines.launch

class CategoryChooserViewModel(
    val categoriesRepository: CategoriesRepository
) : ViewModel() {

    private var items: List<CategoryItem> = emptyList()
    val state = MutableLiveData<State>()

    init {
        viewModelScope.launch {
            state.postValue(State.Progress)
            categoriesRepository.getCategories().fold(
                {
                    items = it.map { CategoryItem(it) }
                    state.postValue(State.Data(items))
                },
                {
                    Log.d("LOOOG", "error", it.error)
                    state.postValue(State.Error)
                }
            )
        }
    }

    fun onCategoryChoosed(categoryItem: CategoryItem) {
        viewModelScope.launch {
            items.forEach {
                categoryItem.isChoosed = it.category.id == categoryItem.category.id
            }
            state.postValue(State.Success(categoryItem))
        }
    }

    sealed class State {
        class Success(val item: CategoryItem) : State()
        class Data(val items: List<CategoryItem>) : State()
        object Error : State()
        object Progress : State()
    }
}