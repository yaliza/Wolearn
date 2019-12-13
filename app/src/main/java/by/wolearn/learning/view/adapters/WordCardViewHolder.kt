package by.wolearn.learning.view.adapters

import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import by.wolearn.R
import by.wolearn.learning.view.entities.WordItem
import by.wolearn.learning.view.entities.WordItemViewState
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_example.view.*
import kotlinx.android.synthetic.main.item_word_card.*
import kotlinx.android.synthetic.main.view_word_details.*

class WordCardViewHolder(override val containerView: View) : LayoutContainer,
    RecyclerView.ViewHolder(containerView) {

    fun bind(item: WordItem) {
        word.text = item.word.name
        pos.text = item.word.pos
        wordDefinition.text = item.word.quiz.options[item.word.quiz.indexOfRight]
        transcription.text = item.word.transcription

        examples.removeAllViews()
        item.word.examples.forEach { example: String ->
            val view =
                LayoutInflater.from(containerView.context)
                    .inflate(R.layout.item_example, null, false)
            view.text.text = example
            examples.addView(view)
        }

        unknown()
        options.resetOptions()
        options.setupTitles(item.word.quiz)
        when (item.viewState) {
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