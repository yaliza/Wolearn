package by.wolearn.settings.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import by.wolearn.R
import com.bumptech.glide.Glide
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
            Glide.with(icon).load(getResourceId(R.styleable.ManageButton_icon, R.drawable.settings_ic_remove)).into(icon)
            text.text = getText(R.styleable.ManageButton_text)
            recycle()
        }
}