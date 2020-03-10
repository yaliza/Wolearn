package by.wolearn.settings.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import by.wolearn.R
import by.wolearn.core.utils.load
import kotlinx.android.synthetic.main.view_manage_button.view.*


class ManageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_manage_button, this)
        initAttributes(attrs)
    }

    private fun initAttributes(attrs: AttributeSet?) =
        context.theme.obtainStyledAttributes(attrs, R.styleable.ManageButton, 0, 0).apply {
            icon.load(getResourceId(R.styleable.ManageButton_icon, R.drawable.settings_ic_remove))
            text.text = getText(R.styleable.ManageButton_text)
            recycle()
        }
}