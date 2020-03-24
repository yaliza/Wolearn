package by.wolearn.mywords.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.wolearn.R
import by.wolearn.core.didSet
import by.wolearn.learning.backend.entities.Word
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_word.*

class MyWordsAdapter : RecyclerView.Adapter<MyWordsAdapter.CategoryViewHolder>() {

    var items: List<Word> by didSet(emptyList()) { notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        val vh = CategoryViewHolder(view)
        return vh
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class CategoryViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView) {

        fun bind(item: Word) {
            word.text = item.name
            pos.text = item.pos
        }
    }
}