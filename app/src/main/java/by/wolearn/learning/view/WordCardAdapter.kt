package by.wolearn.learning.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.wolearn.R
import by.wolearn.core.utils.didSet
import by.wolearn.learning.model.Word
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_word_card.*

class WordCardAdapter : RecyclerView.Adapter<WordCardAdapter.WordCardViewHolder>() {

    var items: List<Word> by didSet(emptyList()) { notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordCardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_word_card, parent, false)
        return WordCardViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: WordCardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class WordCardViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView) {

        fun bind(wrd: Word) {
            word.text = wrd.name
        }
    }
}