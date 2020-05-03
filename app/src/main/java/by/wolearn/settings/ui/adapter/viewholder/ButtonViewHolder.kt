package by.wolearn.settings.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import by.wolearn.R
import by.wolearn.settings.ui.entities.Item
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.item_settings_button)
abstract class ButtonViewHolder : EpoxyModelWithHolder<ButtonViewHolder.Holder>() {

    @EpoxyAttribute
    lateinit var item: Item.Button

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(holder) {
            text.setText(item.text)
            icon.setImageResource(item.image)
            button.setOnClickListener { listener.onButtonClick(item.id) }
        }
    }

    class Holder : KotlinEpoxyHolder() {
        val button by bind<View>(R.id.button)
        val icon by bind<ImageView>(R.id.icon)
        val text by bind<TextView>(R.id.text)
    }

    interface Listener {
        fun onButtonClick(id: String)
    }

}