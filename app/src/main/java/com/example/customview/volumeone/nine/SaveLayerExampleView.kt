package com.example.customview.volumeone.nine

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class SaveLayerExampleView: View {
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

    private val mBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.dog
        )
    }

    private val mPaint: Paint by lazy {
        Paint().apply {
            color = Color.RED
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawBitmap(
                mBitmap,
                0f,
                0f,
                mPaint
            )

            val layerId = saveLayerAlpha(
                RectF(
                    0f,
                    0f,
                    200f,
                    200f
                ),
               100
            )
            drawColor(Color.WHITE)

            restoreToCount(layerId)
        }
    }
}