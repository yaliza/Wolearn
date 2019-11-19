package by.wolearn.learning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.learning.model.LearningRepository
import by.wolearn.learning.model.Word
import by.wolearn.learning.model.WordState
import by.wolearn.learning.view.entities.WordItem
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.coroutines.launch


class LearningViewModel(val repository: LearningRepository) : ViewModel() {

    val words = MutableLiveData<List<WordItem>>()

    init {
        words.value = listOf(
            WordItem(Word("test", listOf("d1", "d2", "d3", "d4"), "adj", "trans")),
            WordItem(Word("test2", listOf("d1", "d2", "d3", "d4"), "adj", "trans")),
            WordItem(Word("test3", listOf("d1", "d2", "d3", "d4"), "adj", "trans")),
            WordItem(Word("test4", listOf("d1", "d2", "d3", "d4"), "adj", "trans")),
            WordItem(Word("test5", listOf("d1", "d2", "d3", "d4"), "adj", "trans"))
        )
    }

    fun saveWord(wordItem: WordItem) {
        viewModelScope.launch {

        }
    }

    fun changeWordState(wordItem: WordItem, direction: Direction?) {
        wordItem.localWordState = when (direction) {
            Direction.Right -> WordState.MEMORIZED
            Direction.Left -> WordState.UNMEMORIZED
            else -> wordItem.word.state
        }
    }

}