package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.customview.R
import kotlin.math.max
import kotlin.math.min

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
        mPaint = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 5f
        }
    }

    private val mBitmap: Bitmap by lazy {
        Bitmap.createBitmap(
            500,
            300,
            Bitmap.Config.ARGB_8888
        ).apply {
            val canvas = Canvas(this)
            val paint = Paint()
            paint.shader = LinearGradient(
                500f / 2 ,
                0f,
                500f / 2,
                300f,
                (0xffffffff).toInt(),
                (0x00ffffff).toInt(),
                Shader.TileMode.CLAMP
            )
            canvas.drawRect(
                0f,
                0f,
                500f,
                300f,
                paint
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
//            Log.e("Bitmap" , "${srcBitmap.isMutable}")
            drawBitmap(mBitmap , 0f, 0f , mPaint)
            drawRect(0f , 0f , mBitmap.width.toFloat() , mBitmap.height.toFloat() , mPaint )
        }
    }

    private val srcBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.p3
        ).run {
            copy(
                Bitmap.Config.ARGB_8888,
                true
            )
        }
    }

    private val colorBitmap by lazy {
        val bitmapWidth = 500
        val bitmapHeight = 300
        Bitmap.createBitmap(
            initColors(bitmapWidth, bitmapHeight),
            bitmapWidth,
            bitmapHeight,
            Bitmap.Config.ARGB_8888
        )
    }

    private fun initColors(width: Int , height: Int): IntArray{
        val colors = IntArray(width * height)
        for(y in 0 until  height){
            for(x in 0 until width){
                val r = x * 255 / (width - 1)
                val g = y * 255 / (width - 1)
                val b = 255 - min(r , g)
                val a = max(r , g)
                colors[y * width + x] = Color.argb(r , g,  b , a)
            }
        }
        return  colors
    }
}