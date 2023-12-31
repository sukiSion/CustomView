package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.util.AttributeSet
import android.view.View

class AntiAliasView: View {
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


    private val paint by lazy {
        Paint().apply {
            isAntiAlias = false
            color = Color.CYAN
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val drawable = ShapeDrawable(RectShape()).apply {
            setBounds(0 , 0 , 100 ,100)
        }.draw(canvas!!)
        canvas?.drawCircle(
            100f,
            100f,
            100f,
            paint
        )
    }
}