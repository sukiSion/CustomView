package com.example.customview.volumeone.eight

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class LightBookView: View{
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

    private val mPaint: Paint
    private val desBitmap: Bitmap
    private val srcBitmap: Bitmap

    init{
        mPaint = Paint()
        desBitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.book_bg
        )
        srcBitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.book_light
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawWithMode(this){
            canvas.drawBitmap(
                desBitmap,
                0f,
                0f,
                mPaint
            )
            mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.LIGHTEN)
            canvas.drawBitmap(
                srcBitmap,
                0f,
                0f,
                mPaint
            )
        }
    }
}