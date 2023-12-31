package com.example.customview.volumeone.eight

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.customview.R

class TelescopeView: View {
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
    private val mBitmap: Bitmap
    private var mBitmapBG: Bitmap? = null
    private var mDx: Float = -1f
    private var mDy: Float = -1f

    init {
        mPaint = Paint()
        mBitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.scenery
        )
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            when(action){
                MotionEvent.ACTION_DOWN -> {
                    mDx = x
                    mDy = y
                    postInvalidate()
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    mDx = x
                    mDy = y
                }
                MotionEvent.ACTION_UP -> {
                    mDx = -1f
                    mDy = -1f
                }
                MotionEvent.ACTION_CANCEL -> {
                    mDx = -1f
                    mDy = -1f
                }
            }
            postInvalidate()
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(mBitmapBG == null){
            mBitmapBG = Bitmap.createBitmap(
                width,
                height,
                Bitmap.Config.ARGB_8888
            ).also {
                val bitmapCanvas = Canvas(it)
                bitmapCanvas.drawBitmap(
                    mBitmap,
                    null,
                    RectF(
                        0f,
                        0f,
                        width.toFloat(),
                        height.toFloat()
                    ),
                    mPaint
                )

            }
        }else{
            if(mDx != -1f && mDy != -1f){
                mBitmapBG?.apply {
                    mPaint.shader = BitmapShader(
                        this,
                        Shader.TileMode.REPEAT,
                        Shader.TileMode.REPEAT
                    )
                    canvas?.drawCircle(mDx , mDy , 150f , mPaint)
                }
            }
        }
    }
}