package by.wolearn.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import by.wolearn.R
import by.wolearn.core.utils.load
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.view_action_button.view.*

const val DEFAULT_IMAGE = R.drawable.ic_eye

class ActionButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_action_button, this)
        initAttributes(attrs)
    }

    private fun initAttributes(attrs: AttributeSet?) =
        context.theme.obtainStyledAttributes(attrs, R.styleable.ActionButton, 0, 0).apply {
            actionButtonImage.load(
                getResourceId(R.styleable.ActionButton_action_image, DEFAULT_IMAGE)
            )
            recycle()
        }
}