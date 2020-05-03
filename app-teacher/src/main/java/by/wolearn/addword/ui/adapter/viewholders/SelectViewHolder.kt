package by.wolearn.addword.ui.adapter.viewholders

import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import by.wolearn.R
import by.wolearn.addword.ui.entities.Item
import by.wolearn.core.ui.BaseTextWatcher
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.textfield.TextInputLayout


@EpoxyModelClass(layout = R.layout.item_addword_select)
abstract class SelectViewHolder : EpoxyModelWithHolder<SelectViewHolder.Holder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    @EpoxyAttribute
    lateinit var select: Item.Select

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.textLayout.hint = holder.context?.getString(select.hint)
        holder.textLayout.error = select.error
        setupAutocomplete(holder)
    }

    private fun setupAutocomplete(holder: Holder) {
        val ctx = holder.context ?: return
        val adapter = ArrayAdapter(ctx, R.layout.support_simple_spinner_dropdown_item, select.items)
        holder.autocomplete.setAdapter(adapter)
        holder.autocomplete.addTextChangedListener(object : BaseTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                listener.onSelected(select, s.toString())
            }
        })
    }

    class Holder : KotlinEpoxyHolder() {
        val autocomplete by bind<AutoCompleteTextView>(R.id.autocomplete)
        val textLayout by bind<TextInputLayout>(R.id.inputLayout)
    }

    interface Listener {
        fun onSelected(selectItem: Item.Select, value: String)
    }
}