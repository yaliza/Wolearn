package by.wolearn.learning.ui.entities

import by.wolearn.learning.backend.entities.Word


data class WordItem(
    val word: Word,
    var viewState: WordItemViewState = WordItemViewState.PREVIEW
)
