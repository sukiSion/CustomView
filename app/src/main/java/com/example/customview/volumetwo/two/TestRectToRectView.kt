package com.example.customview.volumetwo.two

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.customview.R

class TestRectToRectView : View {
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

    private var viewWidth = 0f
    private var viewHeight = 0f
    private val mBitmap = BitmapFactory.decodeResource(context.resources , R.drawable.cat1)
    private val mMatrix = Matrix()
    private val mPaint = Paint().also { it.color = Color.YELLOW }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w.toFloat()
        viewHeight = h.toFloat()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.save()
//        // src
//        val rectF = RectF(0f , 0f , mBitmap.width.toFloat() , mBitmap.height.toFloat())
//        // dst
//        val rectF1 = RectF(0f , 0f , viewWidth , viewHeight)
//        canvas?.drawRect(rectF1 , mPaint)
//
//        mMatrix.setRectToRect(rectF , rectF1 , Matrix.ScaleToFit.CENTER)
//        canvas?.setMatrix(mMatrix)
//        canvas?.drawBitmap(mBitmap , 0f, 0f , mPaint )
//
//        canvas?.restore()
//        isIdentity()
        setValues()

    }

    private fun isRect(){
        mMatrix.setSkew(1f, 0f)
//        mMatrix.setRotate(90f)
//        mMatrix.setScale(0.5f, 0.5f)
        val result = mMatrix.rectStaysRect()
        Log.e("Sion" , "result: $result")
    }

    private fun isIdentity(){
        var isIdentity = mMatrix.isIdentity
        Log.e("Sion" , "before: $isIdentity")
        mMatrix.postSkew(1f , 0f)
        isIdentity = mMatrix.isIdentity
        Log.e("Sion" , "after: $isIdentity")
    }

    private fun getValues(){
        mMatrix.setRotate(15f)
        mMatrix.preTranslate(100f , 200f)
        val values = FloatArray(9)
        mMatrix.getValues(values)

        Log.e("Sion" , "matrix: ${mMatrix.toShortString()}")
        Log.e("Sion" , "values: ${values.contentToString()}")
    }

    private fun setValues(){
        val values = floatArrayOf(0.9659258f, -0.25881904f, 44.828773f, 0.25881904f, 0.9659258f, 219.06708f, 0.0f, 0.0f, 1.0f)

        Log.e("Sion" , "matrix: ${mMatrix.toShortString()}")
        mMatrix.setValues(values)
        Log.e("Sion" , "matrix: ${mMatrix.toShortString()}")


    }
}