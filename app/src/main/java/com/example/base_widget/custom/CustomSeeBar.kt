package com.example.base_widget.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import com.example.base_widget.R

class CustomSeeBar : FrameLayout {

    private val bgBorderPaint = Paint()
    private val bgBorderRect = RectF()

    private val pgPaint = Paint()
    private val pgRect = RectF()

    private val thumbPaint = Paint()
    private val thumbRect = RectF()
    var bgBorderColor: Int = Color.BLACK
        set(value) {
            field = value
            bgBorderPaint.color = field
            invalidate()
        }

    var isPetThumb: Boolean? = false

    var progressColor: Int = Color.BLACK
        set(value) {
            field = value
            pgPaint.color = field
            invalidate()
        }

    var progress = 0

    private var rectFLeftBG = 0f
    private var rectFTopBG = 0f
    private var rectFRightBG = 0f
    private var rectFBottomBG = 0f
    private var rectBgRound = 0f

    private var rectFLeftPG = 0f
    private var rectFTopPG = 0f
    private var rectFRightPG = 0f
    private var rectFBottomPG = 0f
    private var rectPgRound = 0f

    private var rectFLeftT = 0f
    private var rectFTopT = 0f
    private var rectFRightT = 0f
    private var rectFBottomT = 0f

    private var thumbHeight = 0f

    private var petThumbDrawable: Drawable? = null
    private var plantThumbDrawable: Drawable? = null


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
        setBackgroundColor(Color.TRANSPARENT)
        bgBorderPaint.isAntiAlias = true
        pgPaint.isAntiAlias = true
        thumbPaint.isAntiAlias = true

        petThumbDrawable = context.getDrawable(R.drawable.ic_cat_hand_thumb)
        plantThumbDrawable = context.getDrawable(R.drawable.ic_leaves_thumb)


        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomSeekBar,
            defStyleAttr,
            defStyleRes
        )

        bgBorderColor =
            typedArray.getColor(R.styleable.CustomSeekBar_ct_backgroundBorderColor, Color.BLACK)
        isPetThumb = typedArray.getBoolean(R.styleable.CustomSeekBar_ct_is_pet_thumb, false)
        progressColor = typedArray.getColor(R.styleable.CustomSeekBar_ct_progressColor, Color.BLACK)
        progress = typedArray.getInt(R.styleable.CustomSeekBar_ct_progress, 0)

        typedArray.recycle()
    }

    fun setValue(value: Int) {
        val newLeft = value.toFloat()
        val newRight = newLeft + thumbHeight
        Log.e("huynq", "newLeft: ${newLeft}")
        Log.e("huynq", "newRight: ${newRight}")
        Log.e("huynq", "thumbHeight: ${thumbHeight}")
        thumbRect.set(newLeft, thumbRect.top, newRight, thumbRect.top + thumbHeight)
        invalidate()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBorder(canvas)
        drawProgress(canvas)
        drawThumb(canvas)
    }

    private fun drawThumb(canvas: Canvas) {
        if (isPetThumb == true) {
            petThumbDrawable?.let {
                petThumbDrawable?.setBounds(
                    rectFLeftT.toInt(),
                    rectFTopT.toInt(),
                    rectFRightT.toInt(),
                    rectFBottomT.toInt()
                )
                petThumbDrawable?.draw(canvas)
            }
        } else {
            plantThumbDrawable?.let {
                plantThumbDrawable?.setBounds(
                    rectFLeftT.toInt(),
                    rectFTopT.toInt(),
                    rectFRightT.toInt(),
                    rectFBottomT.toInt()
                )
                plantThumbDrawable?.draw(canvas)
            }
        }
    }

    private fun drawProgress(canvas: Canvas) {
        pgPaint.style = Paint.Style.FILL
        pgRect.right = thumbRect.centerX()
        canvas.drawRoundRect(pgRect, rectPgRound, rectPgRound, pgPaint)
    }

    private fun drawBorder(canvas: Canvas) {
        bgBorderPaint.style = Paint.Style.STROKE
        bgBorderPaint.strokeWidth = 2f
        canvas.drawRoundRect(bgBorderRect, rectBgRound, rectBgRound, bgBorderPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        //progress
        rectFLeftPG = 0f
        rectFTopPG = (height * 0.4).toFloat()
        rectFRightPG = (width).toFloat()
        rectFBottomPG = (height * 0.6).toFloat()
        rectPgRound = (rectFBottomPG - rectFTopPG)
        pgRect.set(rectFLeftPG, rectFTopPG, rectFRightPG, rectFBottomPG)

        //border
        rectFLeftBG = 0f
        rectFTopBG = (height * 0.4).toFloat()
        rectFRightBG = (width).toFloat()
        rectFBottomBG = (height * 0.6).toFloat()
        rectBgRound = (rectFBottomBG - rectFTopBG)
        bgBorderRect.set(rectFLeftBG, rectFTopBG, rectFRightBG, rectFBottomBG)

        //thumb
        thumbHeight = (height * 0.6).toFloat()

//        rectFLeftT = 0f
//        rectFTopT = (height * 0.2).toFloat()
//        rectFBottomT = (height * 0.8).toFloat()
//        rectFRightT = rectFBottomT - rectFTopT

        rectFLeftT = (height - thumbHeight) / 2f
        rectFTopT = (height * 0.8).toFloat() - thumbHeight
        rectFRightT = rectFLeftT + thumbHeight
        rectFBottomT = rectFTopT + thumbHeight
        thumbRect.set(rectFLeftT, rectFTopT, rectFRightT, rectFBottomT)

        invalidate()
        isCreated = true
        listener?.isCreated()
    }

    private var listener: CreatedListener? = null
    var isCreated = false
    fun setViewCreatedListener(listener: CreatedListener) {
        this.listener = listener
    }
}

interface CreatedListener {
    fun isCreated()
}