package by.wolearn.learning.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.fold
import by.wolearn.learning.backend.entities.Word
import by.wolearn.learning.data.LearningRepository
import by.wolearn.learning.ui.entities.WordItem
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.coroutines.launch


class LearningViewModel(private val repository: LearningRepository) : ViewModel() {

    private val list = mutableListOf<WordItem>()

    val state = MutableLiveData<State>()
    val swipeCard = MutableLiveData<Direction>()

    init {
        state.postValue(State.Progress)
        loadWords()
    }

    fun loadWords() {
        viewModelScope.launch {
            repository.getWords().fold(
                { handleData(it) },
                { handleException(it) }
            )
        }
    }

    fun saveWord(wordItem: WordItem, direction: Direction?) {
        viewModelScope.launch {
            val isMemorize = direction == Direction.Right
            repository.saveWord(wordItem.word.id, isMemorize).fold(
                {
                    list.removeAt(0)
                    if (list.size == MIN_WORDS_NUMBER) loadWords()
                },
                { handleException(it) }
            )
        }
    }

    private fun handleData(words: List<Word>) {
        val wordsItems = words.map { WordItem(it) }
        list.addAll(wordsItems)
        state.postValue(State.Data(list.toList()))
    }

    private fun <T> handleException(error: Resource.Error<T>) {
        when (error) {
            is Resource.Error.ApiError -> state.postValue(State.Error(error.exception.message))
            is Resource.Error.UnknownError -> state.postValue(State.UnknownError)
        }
    }

    sealed class State {
        class Data(val items: List<WordItem>) : State()
        class Error(val message: String) : State()
        object Progress : State()
        object UnknownError : State()
    }

    companion object{
        private const val MIN_WORDS_NUMBER = 2
    }
}