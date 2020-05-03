package by.wolearn.learningmode.ui.adapter

import by.wolearn.core.didSet
import by.wolearn.learningmode.ui.adapter.viewholder.OptionViewHolder
import by.wolearn.learningmode.ui.adapter.viewholder.optionViewHolder
import by.wolearn.learningmode.ui.entities.OptionItem
import com.airbnb.epoxy.EpoxyController

class LearningModeController(val listener: Listener) : EpoxyController() {

    var items: List<OptionItem> by didSet(emptyList()) { requestModelBuild() }

    override fun buildModels() {
        items.forEach {
            optionViewHolder {
                id(it.id)
                item(it)
                listener(listener)
            }
        }
    }

    interface Listener : OptionViewHolder.Listener

}