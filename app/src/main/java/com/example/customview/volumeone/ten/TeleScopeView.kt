package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.customview.R

class TeleScopeView:View {
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

    companion object{
        const val RADIUS = 80
        const val FACTOR = 3
    }

    private val matrix = Matrix()
    private  var mBitmap: Bitmap? = null
    private  var mDrawable: ShapeDrawable? = null

    init {
        setLayerType(LAYER_TYPE_SOFTWARE , null)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        event?.apply {
            matrix.setTranslate(
                RADIUS - x * FACTOR,
                RADIUS - y * FACTOR
            )
            mDrawable?.apply {
                paint.shader.setLocalMatrix(matrix)
                bounds = Rect(
                    x.toInt() - RADIUS,
                    y.toInt() - RADIUS,
                    x.toInt() + RADIUS,
                    y.toInt() + RADIUS
                )
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(mBitmap == null){
            mBitmap = BitmapFactory.decodeResource(
                resources,
                R.drawable.scenery
            ).run {
                Bitmap.createScaledBitmap(
                    this,
                    width,
                    height,
                    false
                )
            }
            mBitmap?.let {
                    bitmap ->
                val shader = BitmapShader(
                    Bitmap.createScaledBitmap(
                        bitmap,
                        bitmap.width * FACTOR,
                        bitmap.height * FACTOR,
                        true
                    ),
                    Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP,
                )
                mDrawable = ShapeDrawable(OvalShape()).apply {
                    paint.shader = shader
                    bounds = Rect(
                        0,
                        0,
                        RADIUS * 2,
                        RADIUS * 2
                    )
                }
            }
        }
        canvas?.apply {
            takeIf {
                mDrawable != null && mBitmap != null
            }?.let {
                drawBitmap(
                    mBitmap!!,
                    0f,
                    0f,
                    null
                )
                mDrawable!!.draw(it)
            }
        }
    }


}