package by.wolearn.categories.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.wolearn.R
import by.wolearn.categories.backend.entities.Category
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_category.*
import kotlinx.android.synthetic.main.item_category.view.*


class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    var onCategorySelected: ((Category, Boolean) -> Unit)? = null
    var items: List<Category> by by.wolearn.core.didSet(emptyList()) { notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        val vh = CategoryViewHolder(view)
        view.selectCategory.setOnCheckedChangeListener { _, b ->
            onCategorySelected?.invoke(items[vh.adapterPosition], b)
        }
        return vh
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class CategoryViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView) {

        fun bind(item: Category) {
            category.text = item.name
            selectCategory.isChecked = item.isSelected
            Glide.with( categoryImage).load(item.imageURL).apply(RequestOptions.circleCropTransform()).into( categoryImage)
        }
    }
}