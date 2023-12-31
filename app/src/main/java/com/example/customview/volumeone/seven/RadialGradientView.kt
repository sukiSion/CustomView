package com.example.customview.volumeone.seven

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

class RadialGradientView: View {
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

    private val mPaint: Paint

    init {
        setLayerType(LAYER_TYPE_SOFTWARE , null)
        mPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val radialGradient = RadialGradient(
            width.toFloat() / 2,
            height.toFloat() / 2,
            width.toFloat() / 6,
            intArrayOf(
                (0xffff0000).toInt(),
                (0xff00ff00).toInt(),
                (0xff0000ff).toInt(),
                (0xffffff00).toInt()
            ),
            floatArrayOf(
                0f,
                0.2f,
                0.5f,
                1f
            ),
            Shader.TileMode.MIRROR
        )
        mPaint.shader = radialGradient
        canvas?.drawCircle(
            width.toFloat() / 2,
            height.toFloat() / 2 ,
            width.toFloat() / 2,
            mPaint
        )


    }
}