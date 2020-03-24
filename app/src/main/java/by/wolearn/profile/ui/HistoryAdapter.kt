package by.wolearn.profile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.wolearn.R
import by.wolearn.core.didSet
import by.wolearn.profile.backend.entities.HistoryWord
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_history.*


class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    var items by by.wolearn.core.didSet<List<HistoryWord>>(emptyList()) { notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class HistoryViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView) {

        fun bind(historyItem: HistoryWord) {
            word.text = historyItem.word
            category.text = historyItem.category
            date.text = historyItem.date
        }
    }
}