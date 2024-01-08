package com.example.base_widget.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.base_widget.R

class CustomSeeBar : FrameLayout {

    private val bgBorderPaint = Paint()
    private val bgBorderRect = RectF()
    var bgBorderColor: Int = Color.WHITE
        set(value) {
            field = value

            invalidate()
        }

    var isPetThumb: Boolean? = false

    var progressColor: Int = Color.WHITE

    var progress = 0

    constructor(context: Context) : super(context) {
        initialize(context, null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initialize(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initialize(context, attrs, defStyle, 0)
    }

    private fun initialize(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        bgBorderPaint.isAntiAlias = true

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomSeekBar,
            defStyleAttr,
            defStyleRes
        )

        bgBorderColor =
            typedArray.getColor(R.styleable.CustomSeekBar_ct_backgroundBorderColor, Color.WHITE)
        isPetThumb = typedArray.getBoolean(R.styleable.CustomSeekBar_ct_is_pet_thumb, false)
        progressColor = typedArray.getColor(R.styleable.CustomSeekBar_ct_progressColor, Color.WHITE)
        progress = typedArray.getInt(R.styleable.CustomSeekBar_ct_progress, 0)

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBorder(canvas)
    }

    private fun drawBorder(canvas: Canvas) {
        bgBorderPaint.style = Paint.Style.STROKE
        bgBorderPaint.strokeWidth = 1f
        canvas.drawRoundRect(trackStrokeRect, trackStrokeRound, trackStrokeRound, trackStrokePaint)
    }
}