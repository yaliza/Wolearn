package by.wolearn.addword.ui.adapter.viewholders

import android.widget.Button
import by.wolearn.R
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.item_addword_submit)
abstract class SubmitViewHolder :
    EpoxyModelWithHolder<SubmitViewHolder.Holder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.button.setOnClickListener { listener.onSubmitClick() }
    }

    class Holder : KotlinEpoxyHolder() {
        val button by bind<Button>(R.id.button)
    }

    interface Listener {
        fun onSubmitClick()
    }

}