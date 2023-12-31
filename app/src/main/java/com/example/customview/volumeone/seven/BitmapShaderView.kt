package com.example.customview.volumeone.seven

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class BitmapShaderView: View {
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

    private val paint: Paint
    private val mBitmap: Bitmap

    init {
        mBitmap = BitmapFactory.decodeResource(resources , R.drawable.dog)

        paint = Paint().apply {
            shader = BitmapShader(
                mBitmap ,
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(
            width.toFloat() / 3,
            height.toFloat() / 3,
            width.toFloat() * 2 / 3,
            height.toFloat() * 2 / 3,
            paint
        )
    }

}