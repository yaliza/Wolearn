package by.wolearn.settings.ui.adapter

import by.wolearn.settings.ui.adapter.viewholder.ButtonViewHolder
import by.wolearn.settings.ui.adapter.viewholder.buttonViewHolder
import by.wolearn.settings.ui.entities.Item
import com.airbnb.epoxy.EpoxyController

class SectionController(val items: List<Item.Button>, val listener: Listener) : EpoxyController() {

    init {
        requestModelBuild()
    }

    override fun buildModels() {
        items.forEach {
            buttonViewHolder {
                id(it.id)
                item(it)
                listener(listener)
            }
        }
    }

    interface Listener : ButtonViewHolder.Listener
}