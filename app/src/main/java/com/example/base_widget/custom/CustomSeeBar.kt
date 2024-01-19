package com.example.base_widget.custom

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import com.example.base_widget.R

class CustomSeeBar : FrameLayout {

    private val bgBorderPaint = Paint()
    private val bgBorderRect = RectF()

    private val bgPaint = Paint()
    private val bgRect = RectF()

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

    var maxValue = 100
        set(value) {
            field = value
            invalidate()
        }


    var setOnSeekBar: ISetOnSeekBar? = null

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

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
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
        bgPaint.isAntiAlias = true
        pgPaint.isAntiAlias = true
        thumbPaint.isAntiAlias = true

        petThumbDrawable = AppCompatResources.getDrawable(context, R.drawable.ic_cat_hand_thumb)
        plantThumbDrawable = AppCompatResources.getDrawable(context, R.drawable.ic_leaves_thumb)


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
        maxValue = typedArray.getInt(R.styleable.CustomSeekBar_ct_max_value, 100)

        typedArray.recycle()
    }

    fun setOnSeeBarChangeListener(setOnSeeBarChangeListener: ISetOnSeekBar) {
        this.setOnSeekBar = setOnSeeBarChangeListener
    }

    fun setValue(value: Int) {
        val newLeft = if (value >= maxValue) {
            if (isPetThumb == true) {
                (((maxValue / maxValue) * bgRect.width()) - (thumbHeight))
            } else {
                (((maxValue / maxValue) * bgRect.width()) - ((thumbHeight) + 7f))
            }
        } else {
            if (value <= 0) {
                if (isPetThumb == true) {
                    0f
                } else {
                    -12f
                }
            } else {
//                if (value == 10)
//                {
//                    when(maxValue)
//                    {
//                        150 -> 26f
//                        200 -> 10.4f
//                        250 -> 2.64f
//                        else -> ((((value.toFloat()) / maxValue) * bgRect.width()) - (thumbHeight))
//                    }
//                } else {
                    ((((value.toFloat()) / maxValue) * bgRect.width()) - (thumbHeight))
//                }
            }
        }
        val oldValue = thumbRect.left

        val animator = ValueAnimator.ofFloat(oldValue, newLeft)
        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            thumbRect.set(
                animatedValue,
                thumbRect.top,
                animatedValue + thumbHeight,
                thumbRect.top + thumbHeight
            )
            setOnSeekBar?.onProgressChanged(this, value)
            invalidate()
        }
        animator.duration = 500
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBorder(canvas)
        drawBackground(canvas)
        drawProgress(canvas)
        drawThumb(canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        bgPaint.style = Paint.Style.FILL
        bgPaint.color = Color.WHITE
        canvas.drawRoundRect(bgRect, rectBgRound, rectBgRound, bgPaint)
    }

    private fun drawThumb(canvas: Canvas) {
        if (isPetThumb == true) {
            petThumbDrawable?.let {
                petThumbDrawable?.setBounds(
                    thumbRect.left.toInt(),
                    thumbRect.top.toInt(),
                    thumbRect.right.toInt(),
                    thumbRect.bottom.toInt()
                )
                petThumbDrawable?.draw(canvas)
            }
        } else {
            plantThumbDrawable?.let {
                plantThumbDrawable?.setBounds(
                    thumbRect.left.toInt(),
                    thumbRect.top.toInt(),
                    thumbRect.right.toInt(),
                    thumbRect.bottom.toInt()
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

        //background
        bgRect.set(rectFLeftBG, rectFTopBG, rectFRightBG, rectFBottomBG)

        //thumb
        thumbHeight = (height * 0.8).toFloat() - (height * 0.2).toFloat()

        if (isPetThumb == true) {
            rectFLeftT = 0f
            rectFTopT = (height * 0.8).toFloat() - thumbHeight
            rectFBottomT = rectFTopT + thumbHeight
            rectFRightT = rectFBottomT - rectFTopT
            thumbRect.set(rectFLeftT, rectFTopT, rectFRightT, rectFBottomT)
        } else {
            rectFLeftT = -12f
            rectFTopT = (height * 0.8).toFloat() - thumbHeight
            rectFBottomT = rectFTopT + thumbHeight
            rectFRightT = rectFBottomT - rectFTopT
            thumbRect.set(rectFLeftT, rectFTopT, rectFRightT, rectFBottomT)
        }

//        rectFLeftT = (height - thumbHeight) / 4f
//        rectFTopT = (height * 0.8).toFloat() - thumbHeight
//        rectFRightT = rectFLeftT + thumbHeight
//        rectFBottomT = rectFTopT + thumbHeight

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