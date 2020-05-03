package by.wolearn.addword.ui.adapter.viewholders

import android.widget.TextView
import by.wolearn.R
import by.wolearn.addword.ui.entities.Item
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.item_addword_title)
abstract class TitleViewHolder : EpoxyModelWithHolder<TitleViewHolder.Holder>() {

    @EpoxyAttribute
    lateinit var title: Item.Title

    override fun bind(holder: Holder) {
        holder.title.text = holder.context?.getString(title.value)
    }

    class Holder : KotlinEpoxyHolder() {
        val title by bind<TextView>(R.id.title)
    }

}