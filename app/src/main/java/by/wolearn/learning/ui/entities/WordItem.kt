package by.wolearn.learning.ui.entities

import by.wolearn.core.Word


data class WordItem(
    val word: Word,
    var viewState: WordItemViewState = WordItemViewState.PREVIEW
)
