package by.wolearn.addword.ui.adapter.viewholders

import android.text.Editable
import by.wolearn.R
import by.wolearn.addword.ui.entities.Item
import by.wolearn.core.ui.BaseTextWatcher
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


@EpoxyModelClass(layout = R.layout.item_addword_input)
abstract class InputViewHolder : EpoxyModelWithHolder<InputViewHolder.Holder>() {

    @EpoxyAttribute
    lateinit var input: Item.Input

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    override fun bind(holder: Holder) {
        holder.layout.hint = holder.context?.getString(input.hint)
        holder.layout.error = input.error
        holder.editText.setText(input.value)
        holder.editText.addTextChangedListener(object : BaseTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                listener.onInputChanged(input, s.toString())
            }
        })
    }

    class Holder : KotlinEpoxyHolder() {
        val editText by bind<TextInputEditText>(R.id.value)
        val layout by bind<TextInputLayout>(R.id.layout)
    }

    interface Listener {
        fun onInputChanged(input: Item.Input, newValue: String)
    }

}