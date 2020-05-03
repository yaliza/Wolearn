package by.wolearn.settings.ui.adapter

import by.wolearn.core.didSetWithValue
import by.wolearn.settings.ui.adapter.viewholder.SectionViewHolder
import by.wolearn.settings.ui.adapter.viewholder.labelViewHolder
import by.wolearn.settings.ui.adapter.viewholder.sectionViewHolder
import by.wolearn.settings.ui.entities.Item
import com.airbnb.epoxy.EpoxyController

class SettingsController(private val listener: Listener) : EpoxyController() {

    var items: List<Item> by didSetWithValue(emptyList()) { requestModelBuild() }

    override fun buildModels() {
        items.forEach {
            when (it) {
                is Item.Label -> {
                    labelViewHolder {
                        id(it.label)
                        item(it)
                    }
                }
                is Item.Section -> {
                    sectionViewHolder {
                        id(it.id)
                        item(it)
                        listener(listener)
                    }
                }
            }
        }
    }

    interface Listener : SectionViewHolder.Listener

}