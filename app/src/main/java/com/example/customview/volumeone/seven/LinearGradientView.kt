package com.example.customview.volumeone.seven

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.customview.R
import java.util.logging.SocketHandler

class LinearGradientView: View {
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
        mPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        mPaint.shader = LinearGradient(
//            0f,
//            height.toFloat(),
//            width.toFloat(),
//            height.toFloat(),
//            0xffff0000.toInt(),
//            0xff00ff00.toInt(),
//            Shader.TileMode.CLAMP
//        )
        val colors = intArrayOf(
            (0xffff0000).toInt(),
            (0xff00ff00).toInt(),
            (0xff0000ff).toInt(),
            (0xffffff00).toInt(),
            (0xff00ffff).toInt()
        )
        val pos = floatArrayOf(
            0f,
            0.2f,
            0.4f,
            0.6f,
            1.0f
        )
        mPaint.shader = LinearGradient(
            0f,
            0f,
            width.toFloat() / 2,
            0f,
            colors,
            pos,
            Shader.TileMode.MIRROR
        )
        mPaint.textSize = 50f
        canvas?.drawText(
            "若能与你再次相见",
            0f , 200f ,
            mPaint
        )
    }
}