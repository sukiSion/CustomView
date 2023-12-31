package com.example.customview.volumeone.nine

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class RestoreToCountView: View {
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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val tag = RestoreToCountView::class.java.name
        canvas?.apply {
            val id1 = save()
            clipRect(
                0f,
                0f,
                800f,
                800f
            )
            drawColor(Color.RED)
            Log.e(tag , "count: ${saveCount} id1: ${id1}")

            val id2 = saveLayer(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                mPaint
            )
            clipRect(
                100f,
                100f,
                700f,
                700f
            )
            drawColor(Color.GREEN)
            Log.e(tag , "count: ${saveCount} id1: ${id2}")

            val id3 = saveLayerAlpha(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                0xFF
            )
            clipRect(
                200f,
                200f,
                600f,
                600f
            )
            drawColor(Color.YELLOW)
            Log.e(tag , "count: ${saveCount} id1: ${id3}")

            val id4 = save()
            clipRect(
                300f,
                300f,
                500f,
                500f
            )
            drawColor(Color.BLUE)
            Log.e(tag , "count: ${saveCount} id1: ${id4}")

            restore()
            restore()
            drawColor(Color.GRAY)
            Log.e(tag , "count: ${saveCount}")

        }
    }
}