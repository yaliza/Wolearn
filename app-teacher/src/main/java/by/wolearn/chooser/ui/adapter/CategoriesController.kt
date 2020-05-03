package by.wolearn.chooser.ui.adapter

import by.wolearn.chooser.ui.entities.CategoryItem
import by.wolearn.core.didSetWithValue
import com.airbnb.epoxy.EpoxyController

class CategoriesController(
    private val listener: Listener
) : EpoxyController() {

    var items: List<CategoryItem> by didSetWithValue(emptyList()) { requestModelBuild() }

    override fun buildModels() {
        items.forEach {
            categoryViewHolder {
                id(it.category.id)
                category(it)
                listener(listener)
            }
        }
    }

    interface Listener : CategoryViewHolder.Listener
}