package by.wolearn.chooser.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import by.wolearn.R
import by.wolearn.chooser.ui.entities.CategoryItem
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@EpoxyModelClass(layout = R.layout.item_category)
abstract class CategoryViewHolder : EpoxyModelWithHolder<CategoryViewHolder.Holder>() {

    @EpoxyAttribute
    lateinit var category: CategoryItem

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    override fun bind(holder: Holder) {
        holder.container.setOnClickListener {
            listener.onCategoryChoosed(category)
        }

        holder.title.text = category.category.name
        Glide.with(holder.image)
            .load(category.category.imageURL)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.image)
        holder.radioButton.isChecked = category.isChoosed
    }

    class Holder : KotlinEpoxyHolder() {
        val title by bind<TextView>(R.id.title)
        val image by bind<ImageView>(R.id.categoryImage)
        val container by bind<View>(R.id.categoryChooser)
        val radioButton by bind<RadioButton>(R.id.radioButton)
    }

    interface Listener {
        fun onCategoryChoosed(categoryItem: CategoryItem)
    }

}