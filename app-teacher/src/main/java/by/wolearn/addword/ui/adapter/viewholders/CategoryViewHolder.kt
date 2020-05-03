package by.wolearn.addword.ui.adapter.viewholders

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import by.wolearn.R
import by.wolearn.addword.ui.entities.Item
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@EpoxyModelClass(layout = R.layout.item_addword_category)
abstract class CategoryViewHolder :
    EpoxyModelWithHolder<CategoryViewHolder.Holder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    @EpoxyAttribute
    lateinit var category: Item.Category

    override fun bind(holder: Holder) {
        super.bind(holder)
        if (category.value == null) loadDefaultImage(holder) else loadImage(holder)
        setupError(holder)
        holder.button.setOnClickListener { listener.onCategoryClick(category) }
    }

    private fun setupError(holder: Holder) {
        with(holder.error) {
            visibility = if (category.error == null) View.GONE else View.VISIBLE
            text = category.error
        }
    }

    private fun loadImage(holder: Holder) {
        holder.title.text = category.value?.name
        Glide.with(holder.categoryImage)
            .load(category.value?.imageURL)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.categoryImage)
    }

    private fun loadDefaultImage(holder: Holder) {
        Glide.with(holder.categoryImage)
            .load(ColorDrawable(ContextCompat.getColor(holder.button.context, R.color.grey)))
            .apply(RequestOptions.circleCropTransform())
            .into(holder.categoryImage)
        holder.title.setText(R.string.addword_no_category)
    }

    class Holder : KotlinEpoxyHolder() {
        val button by bind<View>(R.id.categoryChooser)
        val categoryImage by bind<ImageView>(R.id.categoryImage)
        val title by bind<TextView>(R.id.title)
        val error by bind<TextView>(R.id.error)
    }

    interface Listener {
        fun onCategoryClick(categoryItem: Item.Category)
    }

}