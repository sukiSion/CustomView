package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.customview.R

class DensityView: View {
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

    private val srcBitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.cat
        ).apply {
        }
    }

    private val mPaint by lazy {
        Paint()
    }




    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            drawBitmap(
                srcBitmap,
                0f,
                0f,
                mPaint
            )
            Log.e("Bitmap" , "density: ${srcBitmap.density} width: ${srcBitmap.width} height: ${srcBitmap.height}")
            srcBitmap.density = 880
            Log.e("Bitmap" , "density: ${srcBitmap.density} width: ${srcBitmap.width} height: ${srcBitmap.height}")

            drawBitmap(
                srcBitmap,
                srcBitmap.width.toFloat(),
                0f,
                mPaint
            )
        }

    }
}