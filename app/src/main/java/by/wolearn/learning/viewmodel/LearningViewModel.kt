package by.wolearn.learning.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.mapResource
import by.wolearn.learning.model.entities.Word
import by.wolearn.learning.model.repositories.LearningRepository
import by.wolearn.learning.view.entities.WordItem
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.coroutines.launch


class LearningViewModel(val repository: LearningRepository) : ViewModel() {

    val words: LiveData<Resource<List<WordItem>>> = liveData {
        emit(repository.getWords().mapResource { it?.map { word: Word -> WordItem(word) } })
    }

    fun saveWord(wordItem: WordItem, direction: Direction?) {
        viewModelScope.launch {

        }
    }

}