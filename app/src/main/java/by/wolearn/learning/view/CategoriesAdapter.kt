package by.wolearn.learning.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.wolearn.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_category.*


class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = 20

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind()
    }

    class CategoryViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView) {

        fun bind() {
            category.text = "Test category"
            Glide.with(categoryImage)
                .load(R.drawable.ic_avatar)
                .apply(RequestOptions().circleCrop())
                .into(categoryImage)
        }
    }
}