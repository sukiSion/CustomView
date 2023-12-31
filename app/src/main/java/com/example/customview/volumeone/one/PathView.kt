package com.example.customview.volumeone.one

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class PathView: View {
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
        val paint = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 5f
        }

//        val path = Path().apply {
//            moveTo(10f , 10f)
//            lineTo(10f , 100f)
//            lineTo(300f , 100f)
//            close()
//        }

        val path = Path().apply {
            moveTo(10f , 10f)
        }

        val rectF = RectF(
            100f,
            10f,
            200f,
            100f
        )

        path.arcTo(
            rectF,
            0f,
            90f,
            true
        )

        canvas?.drawPath(
            path,
            paint
        )
    }


}