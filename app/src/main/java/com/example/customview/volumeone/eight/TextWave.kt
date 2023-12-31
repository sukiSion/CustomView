package com.example.customview.volumeone.eight

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.customview.R

class TextWave : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val itemWaveLength = 600f
    private var mDx = 0f

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    private val mPath by lazy {
        Path()
    }

    private val textBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.text_shade
        )
    }

    private val desBitmap: Bitmap by lazy {
        val srcBitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.text_shade
        )
        Bitmap.createBitmap(
            srcBitmap.width,
            srcBitmap.height,
            Bitmap.Config.ARGB_8888
        )
    }

    private val mPaint: Paint by  lazy {
        Paint().apply {
            color = Color.GREEN
            style = Paint.Style.FILL_AND_STROKE
        }
    }

    init {
        startAnimator()
    }

    private fun startAnimator(){
        ValueAnimator.ofFloat(0f , itemWaveLength).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener {
                mDx = it.animatedValue as Float
                postInvalidate()
            }
        }.start()
    }

    private fun createCanvas(){
        mPath.reset()
        val orginY = textBitmap.height.toFloat() / 2
        val halfWaveLength = itemWaveLength / 2
        mPath.moveTo(-itemWaveLength + mDx , orginY)
        var i = -itemWaveLength
        while(i <= itemWaveLength + width){
            mPath.rQuadTo(
                halfWaveLength / 2,
                -50f,
                halfWaveLength,
                0f
            )
            mPath.rQuadTo(
                halfWaveLength / 2,
                50f,
                halfWaveLength,
                0f
            )
            i += itemWaveLength
        }
        mPath.lineTo(
            textBitmap.width.toFloat(),
            textBitmap.height.toFloat()
        )
        mPath.lineTo(
            0f,
            textBitmap.height.toFloat()
        )
        mPath.close()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        createCanvas()
        Canvas(desBitmap).apply {
            drawColor(Color.BLACK , PorterDuff.Mode.CLEAR)
            drawPath(mPath , mPaint)
        }
        canvas?.apply {
            drawBitmap(
                textBitmap,
                0f,
                0f,
                mPaint
            )

            drawWithMode(this@TextWave){
                drawBitmap(
                    desBitmap,
                    0f,
                    0f,
                    mPaint
                )
                mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
                drawBitmap(
                    textBitmap,
                    0f,
                    0f,
                    mPaint
                )
                mPaint.xfermode = null

            }
        }

    }


}