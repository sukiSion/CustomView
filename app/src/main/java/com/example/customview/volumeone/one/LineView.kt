package com.example.customview.volumeone.one

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class LineView: View {
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


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        val paint = Paint().apply {
//            color = Color.RED
//            style = Paint.Style.FILL_AND_STROKE
//            strokeWidth = 50f
//        }
//        canvas?.drawLine(
//            100f,
//            100f,
//            200f,
//            200f,
//            paint
//        )
        val paint = Paint().apply {
            color = Color.RED
            strokeWidth = 15f
        }
        canvas?.drawPoint(
            100f,
            100f,
            paint
        )
    }
}