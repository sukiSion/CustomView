package com.example.customview.volumeone.seven

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class BezierView: View {
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
        rQuadTo(canvas)
    }

    private fun rQuadTo(canvas: Canvas?){
        val  path = Path().run {
            moveTo(100f , 300f)
            rQuadTo(100f ,  -100f , 200f  , 0f)
            rQuadTo(100f , 100f , 200f , 0f)
            this
        }
        canvas?.drawPath(
            path,
            Paint().apply {
                color = Color.BLACK
                style = Paint.Style.STROKE
                strokeWidth = 10f
            }
        )
    }

    private fun quadTo(canvas: Canvas?){
        val path = Path().run {
            moveTo(100f , 300f)
            quadTo(200f , 200f , 300f ,300f)
            quadTo(400f , 400f , 500f, 300f)
            this
        }
        canvas?.drawPath(
            path,
            Paint().apply {
                color = Color.BLACK
                style = Paint.Style.STROKE
                strokeWidth = 10f
            }
        )
    }
}