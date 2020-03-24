package by.wolearn.mywords.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.core.fold
import by.wolearn.learning.backend.entities.Word
import by.wolearn.mywords.data.MyWordsRepository
import kotlinx.coroutines.launch


class MyWordsViewModel(private val repository: MyWordsRepository) : ViewModel() {

    val state = MutableLiveData<State>()

    init {
        loadWords()
    }

    fun loadWords() {
        viewModelScope.launch {
            repository.getWords().fold(
                { state.postValue(State.Data(it)) },
                { state.postValue(State.Error) }
            )
        }
    }

    sealed class State() {
        data class Data(val items: List<Word>) : State()
        object Progress : State()
        object Error : State()
    }

}