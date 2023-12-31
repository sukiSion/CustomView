package com.example.customview.volumeone.eight

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.customview.R

class EraserView: View {
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

    private var mPreX: Float = 0f
    private var mPreY: Float = 0f

    private val srcBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.dog
        )
    }

    private val desBitmap: Bitmap by lazy {
        val srcBitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.dog
        )
        Bitmap.createBitmap(
            srcBitmap.width,
            srcBitmap.height,
            Bitmap.Config.ARGB_8888
        )
    }

    private val textBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.guaguaka_text
        )
    }

    private val mPath: Path by lazy {
        Path()
    }

    private val mPaint: Paint by lazy {
        Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 45f
            strokeCap = Paint.Cap.ROUND
        }
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(
            textBitmap,
            null,
            RectF(
                0f,
                0f,
                srcBitmap.width.toFloat(),
                srcBitmap.height.toFloat()
            ),
            mPaint
        )

        canvas?.drawWithMode(this){
            canvas.apply {
                val canvas1 = Canvas(desBitmap)
                canvas1.drawPath(
                    mPath,
                    mPaint
                )

                drawBitmap(
                    srcBitmap,
                    0f,
                    0f,
                    mPaint
                )

                mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

                drawBitmap(
                    desBitmap,
                    0f,
                    0f,
                    mPaint
                )

                mPaint.xfermode = null
            }
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        event?.apply {
            when(action){
                MotionEvent.ACTION_DOWN -> {
                    mPath.moveTo(
                        x,
                        y
                    )
                    mPreX = x
                    mPreY = y
                    return  true
                }

                MotionEvent.ACTION_MOVE -> {
                    mPath.quadTo(
                        mPreX ,
                        mPreY,
                        (mPreX + x) / 2,
                        (mPreY + y) / 2
                    )
                    mPreX = x
                    mPreY = y
                }
            }
        }
        postInvalidate()
        return super.onTouchEvent(event)

    }
}