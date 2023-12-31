package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class RectView:View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    private val mPaint by lazy {
        Paint().apply {
            textSize = 30f
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            mPaint.color = Color.RED
            drawRect(
                RectF(
                    10f,
                    10f,
                    600f,
                    600f,
                ),
                mPaint
            )

            mPaint.color = Color.GREEN
            drawRect(
                RectF(
                    30f,
                    30f,
                    570f,
                    570f
                ),
                mPaint
            )

            mPaint.color = Color.BLUE
            drawRect(
                RectF(
                    60f,
                    60f,
                    540f,
                    540f
                ),
                mPaint
            )

            mPaint.color = Color.argb(0x3f , 0xff , 0xff , 0xff)
            drawCircle(
                300f,
                300f,
                100f,
                mPaint
            )

            mPaint.color = Color.GREEN
            drawText(
                "6",
                300f,
                300f,
                mPaint
            )
        }
    }


}