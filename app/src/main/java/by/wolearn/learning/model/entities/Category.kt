package by.wolearn.learning.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Category(
    @PrimaryKey val id: Int,
    val name: String,
    val imageURL: String,
    var isSelected: Boolean
)