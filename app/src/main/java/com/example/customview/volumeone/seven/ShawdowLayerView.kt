package com.example.customview.volumeone.seven

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.scale
import androidx.core.graphics.scaleMatrix
import com.example.customview.R

class ShawdowLayerView : View{
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
    private val bitmap: Bitmap

    init {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        paint = Paint().apply {
            color = Color.BLACK
            textSize = 25f
            setShadowLayer(
                1f,
                10f,
                10f,
                Color.GRAY
            )
            bitmap = BitmapFactory.decodeResource( resources ,  R.drawable.p2).let {
                it.scale(it.width /  20 ,  it.height  / 20)
            }
        }
    }

    fun clearShadowLayer(){
        paint.clearShadowLayer()
        invalidate()
    }

    fun setShadowLayer(){
        paint.setShadowLayer(
            1f,
            10f,
            10f,
            Color.GRAY
        )
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawColor(Color.WHITE)
            drawText("Sion" , 100f  , 100f  ,paint)
            drawCircle(
                300f,
                100f,
                50f,
                paint
            )
            drawBitmap(
                bitmap,
                null,
                RectF(
                    500f, 50f,
                    500f + bitmap.width,
                    50f + bitmap.height
                ),
                paint
            )
        }
    }
}