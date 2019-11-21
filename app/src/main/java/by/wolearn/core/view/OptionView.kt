package by.wolearn.core.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import by.wolearn.R
import by.wolearn.core.utils.didSetWithValue
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.view_option.view.*

class OptionView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_option, this)
        strokeColor = Color.RED
        invalidate()
    }

    var title: String by didSetWithValue("") {
        optionTitle.text = it
    }

}