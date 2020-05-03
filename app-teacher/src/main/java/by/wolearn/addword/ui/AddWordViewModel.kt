package by.wolearn.addword.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.addword.data.AddWordRepository
import by.wolearn.addword.ui.entities.Item
import by.wolearn.core.ApiException
import by.wolearn.core.fold
import by.wolearn.core.ui.entities.Category
import kotlinx.coroutines.launch


class AddWordViewModel(
    private val mapper: AddWordUiMapper,
    private val repository: AddWordRepository
) : ViewModel() {

    val state: MutableLiveData<State> = MutableLiveData()

    init {
        initWordForm()
    }

    fun onExampleChanged(example: Item.Example, newValue: String) {
        viewModelScope.launch {
            mapper.onExampleChanged(example, newValue)
        }
    }

    fun onDeleteExampleClick(example: Item.Example) {
        viewModelScope.launch {
            state.postValue(State.Data(mapper.onDeleteExampleClick(example)))
        }
    }

    fun onSubmitClick() {
        viewModelScope.launch {
            repository.wordInsert(mapper.wordInsert).fold(
                { state.postValue(State.Success) },
                { handleError(it.error) }
            )
        }
    }

    fun onSelected(selectItem: Item.Select, value: String) {
        viewModelScope.launch {
            mapper.onSelected(selectItem, value)
        }
    }

    fun onQuizOptionChanged(quizOption: Item.QuizOption, newValue: String) {
        viewModelScope.launch {
            mapper.onQuizOptionChanged(quizOption, newValue)
        }
    }

    fun onInputChanged(input: Item.Input, newValue: String) {
        viewModelScope.launch {
            mapper.onInputChanged(input, newValue)
        }
    }

    fun onAddExampleClick() {
        viewModelScope.launch {
            state.postValue(State.Data(mapper.onAddExampleClick()))
        }
    }

    fun onCategoryChoosed(category: Category) {
        viewModelScope.launch {
            state.postValue(State.Data(mapper.onCategoryChoosed(category)))
        }
    }

    private fun handleError(error: Throwable) {
        if (error !is ApiException) {
            state.postValue(State.Error)
            return
        }
        state.postValue(State.Data(mapper.handleError(error)))
    }

    private fun initWordForm() {
        viewModelScope.launch {
            state.postValue(State.Progress)
            repository.getPartOfSpeeches().fold(
                { state.postValue(State.Data(mapper.getInitialItems(it))) },
                { state.postValue(State.Error) }
            )
        }
    }

    sealed class State {
        data class Data(val items: List<Item>) : State()
        object Success : State()
        object Error : State()
        object Progress : State()
    }
}