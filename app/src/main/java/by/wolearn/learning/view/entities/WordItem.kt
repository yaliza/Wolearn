package by.wolearn.learning.view.entities

import by.wolearn.learning.model.entities.Word


data class WordItem(
    val word: Word,
    var viewState: WordItemViewState = WordItemViewState.PREVIEW
)
