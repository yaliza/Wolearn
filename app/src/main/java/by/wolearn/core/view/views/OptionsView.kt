package by.wolearn.core.view.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import by.wolearn.R
import by.wolearn.learning.model.entities.Quiz
import kotlinx.android.synthetic.main.view_options.view.*

class OptionsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var indexOfRight = 0
    private var optionViews: List<OptionView>

    init {
        inflate(context, R.layout.view_options, this)
        optionViews = listOf(option1, option2, option3, option4)
        optionViews.forEach { it.setOnClickListener(this) }
    }

    fun setupTitles(quiz: Quiz) {
        indexOfRight = quiz.indexOfRight
        optionViews.forEachIndexed { i, opt -> opt.title = quiz.options[i] }
    }

    override fun onClick(view: View) {
        if (view !is OptionView) return
        val optionIndex = optionViews.indexOf(view)
        if (optionIndex != indexOfRight) view.strokeColor = Color.RED
        optionViews[indexOfRight].strokeColor = Color.GREEN
        optionViews.forEach { it.isClickable = false }
    }

    fun resetOptions() {
        optionViews.forEach {
            it.isClickable = true
            it.strokeColor = Color.BLACK
        }
    }
}