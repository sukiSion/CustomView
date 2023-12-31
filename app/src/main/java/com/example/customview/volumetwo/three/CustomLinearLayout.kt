package com.example.customview.volumetwo.three

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.LinearLayout

class CustomLinearLayout: LinearLayout {
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

    private val mBitmap by lazy {
        Bitmap.createBitmap(
            width,
            height,
            Bitmap.Config.ARGB_8888
        )
    }

    private val mCanvas by lazy {
        Canvas(mBitmap)
    }

    private val mPaint by lazy {
        val colorMatrix = ColorMatrix(
            floatArrayOf(
                0.213f, 0.715f, 0.072f , 0f , 0f,
                0.213f, 0.715f, 0.072f , 0f , 0f,
                0.213f, 0.715f, 0.072f , 0f , 0f,
                0f, 0f , 0f , 1f , 0f
            )
        )
        Paint().also {
            it.setColorFilter(ColorMatrixColorFilter(colorMatrix))
        }
    }


    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.save()
        // 将子控件绘制到空白的画布上，也就是新建的bitmap上
        super.dispatchDraw(mCanvas)
        // 将这个bitmap使用黑白遮罩的画笔画出来
        canvas?.drawBitmap(mBitmap , 0f , 0f , mPaint)
        canvas?.restore()
    }
}