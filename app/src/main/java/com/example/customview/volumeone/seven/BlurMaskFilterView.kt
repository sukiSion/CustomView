package com.example.customview.volumeone.seven

import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BlurMaskFilterView: View {
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

    private val  paint: Paint

    init {
        setLayerType(LAYER_TYPE_SOFTWARE , null)
        paint = Paint().apply {
            color = Color.BLACK
            setMaskFilter(
                BlurMaskFilter(
                    50f,
                    BlurMaskFilter.Blur.INNER
                )
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(
            200f,
            200f,
            100f,
            paint
        )
    }
}