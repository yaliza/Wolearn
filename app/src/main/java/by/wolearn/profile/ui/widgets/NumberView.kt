package by.wolearn.profile.ui.widgets

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import by.wolearn.R


class NumberView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val ovalPaint = Paint()
    private val textPaint = TextPaint().apply {
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
    }

    private lateinit var bounds: Rect
    private lateinit var oval: RectF

    private var ovalColor: Int = Color.CYAN
    private var ovalSize: Float = 2f
    private var textSize: Float = 2f
    var number: Int = 24
        set(value) {
            field = value
            invalidate()
        }

    init {
        initAttributes(context, attrs)
        initResources()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(oval, ovalPaint)
        textPaint.getTextBounds(number.toString(), 0, number.toString().length, bounds)
        canvas.drawText(
            number.toString(), 0, number.toString().length, 0 + ovalSize / 2,
            0 + ovalSize / 2 + (bounds.bottom - bounds.top) / 2, textPaint
        )
    }

    private fun initResources() {
        bounds = Rect()
        oval = RectF(0f, 0f, ovalSize, ovalSize)
        ovalPaint.color = ovalColor
        textPaint.color = Color.WHITE
        textPaint.textSize = textSize
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.NumberView, 0, 0).apply {
            try {
                ovalSize = getDimension(R.styleable.NumberView_ovalSize, 0f)
                textSize = getDimension(R.styleable.NumberView_textSize, 0f)
                ovalColor = getColor(R.styleable.NumberView_ovalColor, ovalColor)
            } finally {
                recycle()
            }
        }
    }
}