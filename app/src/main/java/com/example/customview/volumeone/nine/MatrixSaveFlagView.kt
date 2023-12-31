package com.example.customview.volumeone.nine

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class MatrixSaveFlagView: View {
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

    init {
        setLayerType(
            LAYER_TYPE_SOFTWARE,
            null
        )

    }

    private val mPaint: Paint by lazy {
        Paint().apply {
            color = Color.GRAY
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            testSaveLayer1(canvas)
        }
    }

    private fun testSaveLayer1(canvas: Canvas){
        canvas.apply {
            drawColor(Color.GRAY)
            saveLayer(
                0f,
                0f,
                300f,
                300f,
                mPaint,
                Canvas.ALL_SAVE_FLAG
            )
            rotate(40f)
            mPaint.color = Color.BLACK
            drawRect(
                100f,
                100f,
                200f,
                200f,
                mPaint
            )
            restore()
        }
    }

    private fun testSaveLayer(canvas: Canvas){
        canvas.apply {
            drawColor(Color.GRAY)
            saveLayer(
                RectF(
                    0f,
                    0f,
                    300f,
                    300f
                ),
                mPaint
            )
            mPaint.color = Color.BLACK
            drawRect(
                100f,
                100f,
                200f,
                200f,
                mPaint
            )
            restore()
        }
    }

    private fun saveClip(canvas: Canvas){
        canvas.apply {
            saveLayer(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                mPaint,
            )
            drawColor(Color.RED)
            save()
            clipRect(100f, 0f , 200f,100f)
            drawColor(Color.GREEN)
            restore()
            drawColor(Color.YELLOW)
        }


    }


    private fun savePosition(canvas: Canvas){
        canvas.apply {
            save()
            rotate(40f)
            drawRect(
                100f,
                0f,
                200f,
                100f,
                mPaint
            )
            restore()

            mPaint.color = Color.BLACK
            drawRect(
                100f,
                0f,
                200f,
                100f,
                mPaint
            )
        }
    }
}