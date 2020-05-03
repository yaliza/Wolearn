package by.wolearn.addword.ui.adapter

import by.wolearn.addword.ui.adapter.viewholders.*
import by.wolearn.addword.ui.entities.Item
import by.wolearn.core.didSetWithValue
import com.airbnb.epoxy.EpoxyController


class AddWordController(private val listener: Listener) : EpoxyController() {

    var items: List<Item> by didSetWithValue(emptyList()) { requestModelBuild() }

    override fun buildModels() {
        items.forEach {
            when (it) {
                is Item.Title -> {
                    titleViewHolder {
                        id(it.id)
                        title(it)
                    }
                }
                is Item.Input -> {
                    inputViewHolder {
                        id(it.id)
                        input(it)
                        listener(listener)
                    }
                }
                is Item.ButtonAddExample -> {
                    addExampleButtonViewHolder {
                        id(it.id)
                        listener(listener)
                    }
                }
                is Item.Example -> {
                    exampleViewHolder {
                        id(it.id)
                        example(it)
                        listener(listener)
                    }
                }
                is Item.Select -> {
                    selectViewHolder {
                        id(it.id)
                        select(it)
                        listener(listener)
                    }
                }
                is Item.QuizOption -> {
                    quizOptionViewHolder {
                        id(it.id)
                        option(it)
                        listener(listener)
                    }
                }
                is Item.Submit -> {
                    submitViewHolder {
                        id(it.id)
                        listener(listener)
                    }
                }
                is Item.Category -> {
                    categoryViewHolder {
                        id(it.id)
                        category(it)
                        listener(listener)
                    }
                }
            }
        }
    }

    interface Listener :
        InputViewHolder.Listener,
        ExampleViewHolder.Listener,
        AddExampleButtonViewHolder.Listener,
        SelectViewHolder.Listener,
        QuizOptionViewHolder.Listener,
        SubmitViewHolder.Listener,
        CategoryViewHolder.Listener
}