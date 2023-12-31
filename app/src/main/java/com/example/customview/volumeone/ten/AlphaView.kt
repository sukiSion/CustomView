package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.scale
import com.example.customview.R

class AlphaView: View {
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

    private val srcBitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.cat_dog_1,
        ).run {
            val bitmap = if(isMutable){
                this
            }else{
                copy(
                    Bitmap.Config.ARGB_8888,
                    true
                )
            }
            Bitmap.createScaledBitmap(
                bitmap,
                bitmap.width / 5,
                bitmap.height / 5,
                true
            )
        }
    }

    private val offestXY = IntArray(2)

    private val alphaBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.cat_dog_1,
            BitmapFactory.Options().apply {

            }
        ).run {
            val bitmap = if(isMutable){
                this
            }else{
                copy(
                    Bitmap.Config.ARGB_8888,
                    true
                )
            }
            Bitmap.createScaledBitmap(
                bitmap,
                bitmap.width / 5,
                bitmap.height / 5,
                true
            )
        }.extractAlpha(
            Paint().apply {
                maskFilter = BlurMaskFilter(
                    20f,
                    BlurMaskFilter.Blur.NORMAL
                )
            },
            offestXY
        )
    }

    private val paint by lazy {
        Paint().apply {
            color = Color.CYAN
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawBitmap(
                alphaBitmap , 0f, 0f , paint
            )
            drawBitmap(
                srcBitmap,
                -offestXY[0].toFloat(),
                -offestXY[1].toFloat(),
                paint
            )
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }
}