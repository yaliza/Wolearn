package by.wolearn.learning.view.adapters

import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import by.wolearn.R
import by.wolearn.learning.view.entities.WordItem
import by.wolearn.learning.view.entities.WordItemViewState
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_word_card.*

class WordCardViewHolder(override val containerView: View) : LayoutContainer,
    RecyclerView.ViewHolder(containerView) {

    fun bind(wrd: WordItem) {
        word.text = wrd.word.name
        unknown()
        options.resetOptions()
        options.setupTitles(wrd.word.definitions, 0)
        when (wrd.viewState) {
            WordItemViewState.PREVIEW -> showPreview()
            WordItemViewState.DETAILS -> showDescription()
            WordItemViewState.OPTIONS -> showOptions()
        }
    }

    fun unknown() {
        unmemorizeWord.isSelected = false
        memorizeWord.isSelected = false
    }

    fun memorize() {
        unmemorizeWord.isSelected = false
        memorizeWord.isSelected = true
    }

    fun unmemorize() {
        unmemorizeWord.isSelected = true
        memorizeWord.isSelected = false
    }

    fun showOptions() {
        animateView(View.GONE, View.GONE, View.VISIBLE)
    }

    fun showPreview() {
        animateView(View.VISIBLE, View.GONE, View.GONE)
    }

    fun showDescription() {
        animateView(View.GONE, View.VISIBLE, View.GONE)
    }

    private fun animateView(
        actionButtonVisibility: Int,
        detailsVisibility: Int,
        optionsVisibility: Int
    ) {
        TransitionManager.beginDelayedTransition(wordCard)
        val constraintSetBefore = ConstraintSet()
        constraintSetBefore.clone(wordCard)

        ConstraintSet().apply {
            clone(constraintSetBefore)
            setVisibility(R.id.options, optionsVisibility)
            setVisibility(R.id.wordDetails, detailsVisibility)
            setVisibility(R.id.showDescription, actionButtonVisibility)
            setVisibility(R.id.showOptions, actionButtonVisibility)
            applyTo(wordCard)
        }
    }
}