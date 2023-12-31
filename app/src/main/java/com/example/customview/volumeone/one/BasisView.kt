package com.example.customview.volumeone.one

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BasisView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )



    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.drawRGB(
//            0xFF,
//            0x00,
//            0xFF
//        )
//        canvas?.drawARGB(
//            0xFF,
//            0xFF,
//            0x00,
//            0xFF
//        )
        canvas?.drawColor(Color.parseColor("#FFFF00FF"))
        val paint = Paint().apply {
            setColor(Color.parseColor("#FFFF0000"))
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = 50f
        }
        canvas?.drawCircle(
            190f,
            200f,
            150f,
            paint
        )
//        paint.setColor(Color.parseColor("#7EFFFF00"))
//        canvas?.drawCircle(
//            190f,
//            200f,
//            100f,
//            paint
//        )
    }
}