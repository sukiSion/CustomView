package com.example.customview.volumeone.eight

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class InvertedImage: View {
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
        Paint()
    }

    private val srcBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.dog
        )
    }

    private val desBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.dog_invert_shade
        )
    }

    private val revertBitmap: Bitmap by lazy {
        val srcBitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.dog
        )
        val matrix = Matrix()
        matrix.setScale(
            1f,
            -1f
        )
        Bitmap.createBitmap(
            srcBitmap,
            0,
            0,
            srcBitmap.width,
            srcBitmap.height,
            matrix,
            true
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawWithMode(this){
            canvas.apply {
                drawBitmap(srcBitmap , 100f , 100f , mPaint)
                save()
                translate(0f , srcBitmap.height.toFloat() + 100f)
                drawBitmap(
                    revertBitmap,
                    100f,
                    0f,
                    mPaint
                )
                mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
                drawBitmap(
                    desBitmap,
                    100f,
                    0f,
                    mPaint
                )
            }
        }
    }
}