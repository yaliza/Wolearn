package by.wolearn.learning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.learning.model.LearningRepository
import by.wolearn.learning.model.Word
import by.wolearn.learning.view.entities.WordItem
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.coroutines.launch


class LearningViewModel(val repository: LearningRepository) : ViewModel() {

    val words = MutableLiveData<List<WordItem>>()

    init {
        words.value = listOf(
            WordItem(Word("test", listOf("d1", "d2", "d3", "d4"), "adj", "trans", listOf("opt1", "opt2", "opt3", "opt4"))),
            WordItem(Word("test2", listOf("d1", "d2", "d3", "d4"), "adj", "trans", listOf("opt1", "opt2", "opt3", "opt4"))),
            WordItem(Word("test3", listOf("d1", "d2", "d3", "d4"), "adj", "trans", listOf("opt1", "opt2", "opt3", "opt4"))),
            WordItem(Word("test4", listOf("d1", "d2", "d3", "d4"), "adj", "trans", listOf("opt1", "opt2", "opt3", "opt4"))),
            WordItem(Word("test5", listOf("d1", "d2", "d3", "d4"), "adj", "trans", listOf("opt1", "opt2", "opt3", "opt4")))
        )
    }

    fun saveWord(wordItem: WordItem, direction: Direction?) {
        viewModelScope.launch {

        }
    }

}