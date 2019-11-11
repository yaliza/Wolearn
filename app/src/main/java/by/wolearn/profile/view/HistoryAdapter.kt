package by.wolearn.profile.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.wolearn.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_history.*


class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount() = 25

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind()
    }

    class HistoryViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView) {

        fun bind() {
            word.text = "Test"
            category.text = "Category"
            date.text = "17 june 2019"
        }
    }
}