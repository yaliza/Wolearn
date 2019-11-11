package by.wolearn.learning.viewmodel

import androidx.lifecycle.ViewModel
import by.wolearn.learning.model.LearningRepository


class LearningViewModel(
    val repository: LearningRepository
) : ViewModel()