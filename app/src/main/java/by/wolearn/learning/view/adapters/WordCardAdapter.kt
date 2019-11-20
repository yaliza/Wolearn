package by.wolearn.learning.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.wolearn.R
import by.wolearn.core.utils.didSet
import by.wolearn.learning.view.entities.WordItem
import by.wolearn.learning.view.entities.WordItemViewState
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.android.synthetic.main.item_word_card.view.*


interface WordCardListener {
    fun onMemorizeWord(wordItem: WordItem)
    fun onUnmemorizeWord(wordItem: WordItem)
}

class WordCardAdapter(private val wordCardListener: WordCardListener?) :
    RecyclerView.Adapter<WordCardViewHolder>() {

    var items: List<WordItem> by didSet(emptyList()) { notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordCardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_word_card, parent, false)
        val holder = WordCardViewHolder(view)
        view.memorizeWord.setOnClickListener { wordCardListener?.onMemorizeWord(items[holder.adapterPosition]) }
        view.unmemorizeWord.setOnClickListener { wordCardListener?.onUnmemorizeWord(items[holder.adapterPosition]) }
        return holder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: WordCardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateWordCardButtons(viewHolder: RecyclerView.ViewHolder?, direction: Direction?) {
        if (viewHolder !is WordCardViewHolder) return
        when (direction) {
            Direction.Left -> viewHolder.unmemorize()
            Direction.Right -> viewHolder.memorize()
            else -> viewHolder.unknown()
        }
    }

    fun updateWordCardViews(viewHolder: RecyclerView.ViewHolder?) {
        if (viewHolder !is WordCardViewHolder) return
        when (items[viewHolder.adapterPosition].viewState) {
            WordItemViewState.OPTIONS -> viewHolder.options()
            WordItemViewState.PREVIEW -> viewHolder.preview()
            WordItemViewState.SHOW_DETAILS -> viewHolder.details()
        }
    }
}