package by.wolearn.learningmode.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.R
import by.wolearn.learningmode.data.LearningMode
import by.wolearn.learningmode.data.LearningModeRepository
import by.wolearn.learningmode.ui.entities.OptionItem
import kotlinx.coroutines.launch


class LearningModeViewModel(val repository: LearningModeRepository) : ViewModel() {

    val state = MutableLiveData<State>()
    val back = MutableLiveData<Unit>()

    init {
        loadLearningMode()
    }

    fun onOptionSelected(id: String) {
        when (id) {
            NEW_ID -> repository.saveLearningMode(LearningMode.NEW)
            REPEAT_ID -> repository.saveLearningMode(LearningMode.REPEAT)
            MIXED_ID -> repository.saveLearningMode(LearningMode.MIXED)
        }
        back.postValue(Unit)
    }

    private fun loadLearningMode() {
        viewModelScope.launch {
            state.postValue(State.Progress)
            val options = listOf(
                OptionItem(NEW_ID, false, R.string.learning_mode_new),
                OptionItem(REPEAT_ID, false, R.string.learning_mode_repeat),
                OptionItem(MIXED_ID, false, R.string.learning_mode_mixed)
            )
            when (repository.getLearningMode()) {
                LearningMode.NEW -> options[0].isSelected = true
                LearningMode.REPEAT -> options[1].isSelected = true
                LearningMode.MIXED -> options[2].isSelected = true
            }
            state.postValue(State.Data(options))
        }
    }

    sealed class State {
        class Data(val items: List<OptionItem>) : State()
        object Progress : State()
    }

    companion object {
        const val NEW_ID = "new_id"
        const val REPEAT_ID = "repeat_id"
        const val MIXED_ID = "mixed_id"
    }
}