package by.wolearn.addword.ui.adapter.viewholders

import android.text.Editable
import android.widget.EditText
import by.wolearn.R
import by.wolearn.addword.ui.entities.Item
import by.wolearn.core.ui.BaseTextWatcher
import by.wolearn.core.ui.getSupportDrawable
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.textfield.TextInputLayout

@EpoxyModelClass(layout = R.layout.item_addword_example)
abstract class ExampleViewHolder : EpoxyModelWithHolder<ExampleViewHolder.Holder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    @EpoxyAttribute
    lateinit var example: Item.Example

    override fun bind(holder: Holder) {
        super.bind(holder)
        setupEndIcon(holder)
        setupEditText(holder)
    }

    private fun setupEndIcon(holder: Holder) {
        holder.inputLayout.endIconDrawable = if (example.isDeletable) {
            holder.context?.getSupportDrawable(R.drawable.ic_delete)
        } else {
            null
        }
        holder.inputLayout.setEndIconOnClickListener { listener.onExampleDelete(example) }
    }

    private fun setupEditText(holder: Holder) {
        with(holder) {
            inputLayout.hint = holder.context?.getString(R.string.addword_example_hint, example.num)
            inputLayout.error = example.error
            editText.setText(example.value)
            editText.addTextChangedListener(object : BaseTextWatcher() {
                override fun afterTextChanged(s: Editable?) {
                    listener.onExampleChanged(example, s?.toString() ?: "")
                }
            })
        }
    }

    class Holder : KotlinEpoxyHolder() {
        val editText by bind<EditText>(R.id.example)
        val inputLayout by bind<TextInputLayout>(R.id.exampleInput)
    }

    interface Listener {
        fun onExampleDelete(example: Item.Example)
        fun onExampleChanged(example: Item.Example, newValue: String)
    }

}