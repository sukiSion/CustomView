package com.example.customview.volumeone.seven

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class AvatorView: View {
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

    private val mBitmap: Bitmap
    private val mPaint: Paint
    private val mBitmapShader: BitmapShader

    init {
        mBitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.p1
        )
        mPaint = Paint()
        mBitmapShader = BitmapShader(
            mBitmap,
            Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val matrix = Matrix()
        val scale = width.toFloat() / mBitmap.width
        matrix.setScale(scale , scale)
        mBitmapShader.setLocalMatrix(matrix)
        mPaint.shader = mBitmapShader
        val half = width.toFloat() / 2
        canvas?.drawCircle(
            half,
            height.toFloat() / 2,
            half,
            mPaint
        )
    }
}