package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class WaterMarkView: View {
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


    private fun createWaterMarkBitmap(srcBitmap: Bitmap , waterMarkBitmap: Bitmap): Bitmap{
        val newBitmap = Bitmap.createBitmap(
            srcBitmap.width,
            srcBitmap.height,
            Bitmap.Config.ARGB_8888
        ).apply {
            val paint = Paint()
            Canvas(this).let {
                canvas ->
                canvas.drawBitmap(
                    srcBitmap,
                    0f,
                    0f,
                    paint
                )
                canvas.drawBitmap(
                    waterMarkBitmap,
                    (srcBitmap.width - waterMarkBitmap.width + 5).toFloat(),
                    (srcBitmap.height - waterMarkBitmap.height + 5).toFloat(),
                    paint
                )
            }
        }

        return newBitmap
    }

    private val srcBitmap:Bitmap
    private val waterMarkBitmap:Bitmap

    init {
        srcBitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.dog
        )
        waterMarkBitmap =  BitmapFactory.decodeResource(
            resources,
            R.drawable.watermark
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val bitmap = createWaterMarkBitmap(
            srcBitmap ,
            waterMarkBitmap
        )
        canvas?.drawBitmap(bitmap , 0f , 0f , null)
    }
}