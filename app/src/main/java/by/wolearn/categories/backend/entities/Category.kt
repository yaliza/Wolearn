package by.wolearn.categories.backend.entities


data class Category(
    val name: String,
    val imageURL: String,
    var isSelected: Boolean = true
)