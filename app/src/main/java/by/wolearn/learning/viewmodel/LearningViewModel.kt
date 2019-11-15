package by.wolearn.learning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.wolearn.learning.model.LearningRepository
import by.wolearn.learning.model.Word


class LearningViewModel(val repository: LearningRepository) : ViewModel() {

    val words = MutableLiveData<List<Word>>()

    init {
        words.value = listOf(
            Word("test", listOf("d1", "d2", "d3", "d4"), "adj", "trans"),
            Word("test2", listOf("d1", "d2", "d3", "d4"), "adj", "trans"),
            Word("test3", listOf("d1", "d2", "d3", "d4"), "adj", "trans"),
            Word("test4", listOf("d1", "d2", "d3", "d4"), "adj", "trans"),
            Word("test5", listOf("d1", "d2", "d3", "d4"), "adj", "trans")
        )
    }

}