package by.wolearn.learning.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.wolearn.learning.view.entities.WordItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_word_card.*

class WordCardViewHolder(override val containerView: View) : LayoutContainer,
    RecyclerView.ViewHolder(containerView) {

    fun bind(wrd: WordItem) {
        word.text = wrd.word.name
    }

    fun unknown() {

    }

    fun memorize() {

    }

    fun unmemorize() {

    }

    fun options() {

    }

    fun preview() {

    }

    fun details() {

    }
}