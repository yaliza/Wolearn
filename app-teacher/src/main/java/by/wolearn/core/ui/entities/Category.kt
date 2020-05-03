package by.wolearn.core.ui.entities

import android.os.Parcelable
import by.wolearn.chooser.backend.entities.CategoryResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
class Category (
    val id: Int,
    val name: String,
    val imageURL: String
) : Parcelable {

    constructor(category: CategoryResponse) : this(category.id, category.name, category.imageURL)
}