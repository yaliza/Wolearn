package by.wolearn.learning.view.entities

import by.wolearn.learning.model.Word
import by.wolearn.learning.model.WordState


data class WordItem(
    val word: Word,
    var viewState: WordItemViewState = WordItemViewState.PREVIEW
)
