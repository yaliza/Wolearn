package by.wolearn.settings.ui.adapter.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.wolearn.R
import by.wolearn.settings.ui.adapter.SectionController
import by.wolearn.settings.ui.entities.Item
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.item_settings_section)
abstract class SectionViewHolder : EpoxyModelWithHolder<SectionViewHolder.Holder>() {

    @EpoxyAttribute
    lateinit var item: Item.Section

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    override fun bind(holder: Holder) {
        super.bind(holder)
        val controller = SectionController(item.items, listener)
        holder.recycler.layoutManager = LinearLayoutManager(holder.context)
        holder.recycler.adapter = controller.adapter
    }

    class Holder : KotlinEpoxyHolder() {
        val recycler by bind<RecyclerView>(R.id.recycler)
    }

    interface Listener : SectionController.Listener
}