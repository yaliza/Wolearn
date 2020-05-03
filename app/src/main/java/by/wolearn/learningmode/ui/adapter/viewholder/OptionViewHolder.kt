package by.wolearn.learningmode.ui.adapter.viewholder

import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import by.wolearn.R
import by.wolearn.learningmode.ui.entities.OptionItem
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.item_learningmode_option)
abstract class OptionViewHolder : EpoxyModelWithHolder<OptionViewHolder.Holder>() {

    @EpoxyAttribute
    lateinit var item: OptionItem

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.title.setText(item.label)
        holder.option.setOnClickListener { listener.onOptionSelected(item.id) }
        holder.radio.isChecked = item.isSelected
        holder.radio.setOnCheckedChangeListener { buttonView, isChecked ->
            listener.onOptionSelected(item.id)
        }
    }

    class Holder : KotlinEpoxyHolder() {
        val option by bind<View>(R.id.chooser)
        val title by bind<TextView>(R.id.title)
        val radio by bind<RadioButton>(R.id.radioButton)
    }

    interface Listener {
        fun onOptionSelected(id: String)
    }
}