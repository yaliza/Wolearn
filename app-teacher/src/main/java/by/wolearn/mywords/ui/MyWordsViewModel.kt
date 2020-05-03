package by.wolearn.mywords.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.core.fold
import by.wolearn.core.Word
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
                {
                    Log.d("LOOOG", "error", it.error)
                    state.postValue(State.Error) }
            )
        }
    }

    sealed class State() {
        data class Data(val items: List<Word>) : State()
        object Progress : State()
        object Error : State()
    }

}