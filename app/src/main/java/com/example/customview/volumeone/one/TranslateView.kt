package com.example.customview.volumeone.one

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class TranslateView:View {
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
//            color = Color.GREEN
//            style = Paint.Style.FILL
//        }
//
//        canvas?.translate(100f, 100f)
//
//        val rect = Rect(
//            0,
//            0,
//            400,
//            220
//        )
//
//        canvas?.drawRect(
//            rect,
//            paint
//        )

        val paint_green = generatePaint(Color.GREEN , Paint.Style.STROKE , 3f);
        val paint_red = generatePaint(Color.RED , Paint.Style.STROKE , 3f)

        val rect = Rect(
            0 , 0 ,400 , 220
        )

        canvas?.drawRect(
            rect,
            paint_green
        )

        canvas?.translate(100f , 100f)


        canvas?.drawRect(
            rect ,
            paint_red
        )
    }


    private fun generatePaint(
        color: Int,
        style: Paint.Style,
        width: Float
    ): Paint = Paint().apply {
        this.color = color
        this.style = style
        this.strokeWidth = width
    }
}