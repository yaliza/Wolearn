package by.wolearn.settings.ui.adapter.viewholder

import android.widget.TextView
import by.wolearn.R
import by.wolearn.settings.ui.entities.Item
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.item_settings_label)
abstract class LabelViewHolder : EpoxyModelWithHolder<LabelViewHolder.Holder>() {

    @EpoxyAttribute
    lateinit var item: Item.Label

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.label.setText(item.label)
    }

    class Holder : KotlinEpoxyHolder() {
        val label by bind<TextView>(R.id.label)
    }

}