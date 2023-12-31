package com.example.customview.volumeone.eight

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.customview.R

class IrregularWaveView: View {
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

    private val desBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.wave_bg,
            BitmapFactory.Options().apply {
                inSampleSize = 2
            }
        )
    }

    private val srcBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.circle_shape,
            BitmapFactory.Options().apply {
                inSampleSize = 3
            }
        )
    }

    private val mPaint: Paint by lazy {
        Paint().apply {
            isAntiAlias = true
        }
    }
    private var mDx: Float = 0f

    private fun startAnimator(){

        ValueAnimator.ofFloat(0f , desBitmap.width.toFloat() - srcBitmap.width.toFloat()).apply {
            duration = 4000
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener {
                mDx = it.animatedValue as Float
                postInvalidate()
            }
        }.start()
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE , null)
        startAnimator()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(
            srcBitmap,
            0f,
            0f,
            mPaint
        )
        canvas?.drawWithMode(this){
            canvas.apply {
                drawBitmap(
                    desBitmap,
                    Rect(
                        mDx.toInt(),
                        0,
                        (mDx + srcBitmap.width).toInt(),
                        srcBitmap.height
                    ),
                    Rect(
                        0,
                        0,
                        srcBitmap.width,
                        srcBitmap.height
                    ),
                    mPaint
                )
                mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
                drawBitmap(
                    srcBitmap,
                    0f,
                    0f,
                    mPaint
                )
                mPaint.xfermode = null
            }
        }
    }
}