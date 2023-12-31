package com.example.customview.volumetwo.three

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CustomImageView1 : AppCompatImageView{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val colorMatrix = ColorMatrix(
        floatArrayOf(
            0.213f, 0.715f, 0.072f , 0f , 0f,
            0.213f, 0.715f, 0.072f , 0f , 0f,
            0.213f, 0.715f, 0.072f , 0f , 0f,
            0f, 0f , 0f , 1f , 0f
        )
    )

    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        canvas?.save()
        setColorFilter(ColorMatrixColorFilter(colorMatrix))
        super.onDraw(canvas)
        canvas?.restore()
    }
}