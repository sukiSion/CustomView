package com.example.customview.volumeone.nine

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BitmapCanvasView: View {
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

    private val mPaint: Paint by lazy {
        Paint().apply {
            color = Color.BLACK
        }
    }

    private val mBmp: Bitmap by lazy {
        Bitmap.createBitmap(
            500,
            500,
            Bitmap.Config.ARGB_8888
        )
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.textSize = 50f
        Canvas(mBmp).drawText("Sion" , 0f , 100f, mPaint)
        canvas?.drawBitmap(
            mBmp,
            0f,
            0f,
            mPaint
        )
    }

}