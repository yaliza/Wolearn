package by.wolearn.learning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.mapResource
import by.wolearn.learning.model.entities.MemorizeWord
import by.wolearn.learning.model.entities.Word
import by.wolearn.learning.model.repositories.LearningRepository
import by.wolearn.learning.view.entities.WordItem
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.coroutines.launch


class LearningViewModel(val repository: LearningRepository) : ViewModel() {

    val words = MutableLiveData<Resource<List<WordItem>>>()
    var isRepeatingMode = true

    fun loadWords() {
        viewModelScope.launch {
            val items = if (isRepeatingMode) repository.getRepeatWords() else repository.getWords()
            val mapedItems = items.mapResource { it?.map { word: Word -> WordItem(word) } }
            words.postValue(mapedItems)
        }
    }

    fun saveWord(wordItem: WordItem, direction: Direction?) {
        viewModelScope.launch {
            when (direction) {
                Direction.Right -> repository.saveWord(MemorizeWord(wordItem.word.id, true))
                Direction.Left -> repository.saveWord(MemorizeWord(wordItem.word.id, false))
            }
        }
    }

}