package com.example.customview.volumeone.seven

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.scale
import com.example.customview.R

class BitmapShadowView: View {
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

    private val alphaBitmap: Bitmap
    private val mBitmap: Bitmap
    private val paint: Paint

    init {
        setLayerType(LAYER_TYPE_SOFTWARE , null)
        mBitmap = BitmapFactory.decodeResource(resources , R.drawable.cat_dog).let {
            it.scale(
                200,
                (200 * (it.height.toFloat() / it.width)).toInt()
            )
        }
        alphaBitmap = mBitmap.extractAlpha()


        paint = Paint().apply {

        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.GRAY
        paint.maskFilter = BlurMaskFilter(10f , BlurMaskFilter.Blur.NORMAL)
        canvas?.apply {
            drawBitmap(
                alphaBitmap,
                null,
                Rect(
                    10,
                    10,
                    10 + alphaBitmap.width,
                    10 + alphaBitmap.height
                ),
                paint
            )
            save()
            translate(alphaBitmap.width.toFloat() , 0f)
            paint.color = Color.BLACK
            drawBitmap(
                alphaBitmap,
                null,
                Rect(
                    10,
                    10,
                    10 + alphaBitmap.width,
                    10 + alphaBitmap.height
                ),
                paint
            )
            restore()
            save()
            translate( -5f , -5f)
            paint.maskFilter = null
            drawBitmap(
                mBitmap,
                null,
                Rect(
                    0,
                    0,
                    0 + mBitmap.width,
                    0 + mBitmap.height
                ),
                paint
            )

        }
    }
}