package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.scale
import com.example.customview.R

class ShapeShaderView:View {
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


    init {
        setLayerType(LAYER_TYPE_SOFTWARE , null)
    }

    private val mShaderDrawable: ShapeDrawable by lazy {
        ShapeDrawable(RectShape()).apply {
            bounds = Rect(
                100,
                100,
                300,
                300
            )

            val bitmap  = BitmapFactory.decodeResource(
                resources,
                R.drawable.p1,
                BitmapFactory.Options().apply {
                    inSampleSize = 20
                }
            )

            paint.shader = BitmapShader(
                bitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            mShaderDrawable.draw(canvas)
        }
    }
}