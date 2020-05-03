package by.wolearn.chooser.ui.entities

import by.wolearn.core.ui.entities.Category

data class CategoryItem(
    val category: Category,
    var isChoosed: Boolean = false
)