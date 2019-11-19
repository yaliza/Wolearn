package by.wolearn.learning.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.wolearn.R
import by.wolearn.core.utils.didSet
import by.wolearn.learning.model.WordState
import by.wolearn.learning.view.entities.WordItem
import by.wolearn.learning.view.entities.WordItemViewState


class WordCardAdapter : RecyclerView.Adapter<WordCardViewHolder>() {

    var items: List<WordItem> by didSet(emptyList()) { notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordCardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_word_card, parent, false)
        return WordCardViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: WordCardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun changeWordState(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        if (viewHolder !is WordCardViewHolder) return
        when (items[position].localWordState) {
            WordState.UNKNOWN -> viewHolder.unknown()
            WordState.UNMEMORIZED -> viewHolder.unmemorize()
            WordState.MEMORIZED -> viewHolder.memorize()
        }
    }

    fun changeWordViewState(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        if (viewHolder !is WordCardViewHolder) return
        when (items[position].viewState) {
            WordItemViewState.OPTIONS -> viewHolder.options()
            WordItemViewState.PREVIEW -> viewHolder.preview()
            WordItemViewState.SHOW_DETAILS -> viewHolder.details()
        }
    }
}