package by.wolearn.learning.viewmodel

import androidx.lifecycle.*
import by.wolearn.learning.model.repositories.LearningRepository
import by.wolearn.learning.view.entities.WordItem
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.coroutines.launch


class LearningViewModel(val repository: LearningRepository) : ViewModel() {

    val words: LiveData<List<WordItem>> = liveData {
        emit(repository.getWords().map { WordItem(it) })
    }

    fun saveWord(wordItem: WordItem, direction: Direction?) {
        viewModelScope.launch {

        }
    }

}