package by.wolearn.learning.viewmodel

import androidx.lifecycle.ViewModel
import by.wolearn.learning.model.LearningRepository


class CategoriesViewModel(
    val repository: LearningRepository
) : ViewModel()