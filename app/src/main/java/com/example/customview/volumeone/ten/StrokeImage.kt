package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.toBitmap

class StrokeImage: AppCompatImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var paint: Paint

    override fun onFinishInflate() {
        super.onFinishInflate()
        paint = Paint().apply {
            color = Color.CYAN
        }
        setStateDrawable(imageView = this , paint)
    }



    private fun setStateDrawable(imageView: ImageView , paint: Paint){
        val srcBitmap:Bitmap = imageView.drawable.toBitmap()
        val mBitmap = Bitmap.createBitmap(
            srcBitmap.width,
            srcBitmap.height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(mBitmap)
        canvas.drawBitmap(
            srcBitmap.extractAlpha(),
            0f,
            0f,
            paint
        )
        val sld = StateListDrawable().apply {
            addState(
                intArrayOf(
                    android.R.attr.state_pressed
                ),
                BitmapDrawable(
                    resources,
                    srcBitmap.extractAlpha()
                )
            )
        }
        imageView.background = sld
    }
}