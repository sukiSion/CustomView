package com.example.customview.volumetwo.two

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Point
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.Arrays

class TestScaleView: View {
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

    private val mPaint:Paint = Paint().also {
        it.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        scale(canvas)
//        skew(canvas)
//        sincos(canvas)
//        mapPoint()
//        mapRadius()
//        mapRect()
        setpolyToPoly()
    }

    private fun setpolyToPoly(){
        val matrix = Matrix()
        val src = floatArrayOf(0f , 0f , 400f , 0f , 0f , 400f , 400f , 400f)
        val dst = floatArrayOf(100f , 200f , 450f , 440f , 120f , 720f , 810f , 940f)
        matrix.setPolyToPoly(src , 0 , dst , 0 , 0)

        // 求出两个数组起始位置第一个点的映射矩阵
        Log.e("Sion" , "matrix: ${matrix.toShortString()}")
        // 此时原矩阵没有发生变化
        Log.e("Sion" , "before = ${src.contentToString()}")

        // 根据这个映射矩阵计算出变换后的新矩阵
        matrix.mapPoints(src)

        // 变换后的矩阵
        Log.e("Sion" , "after: ${src.contentToString()}")
        Log.e("Sion" , "after: ${dst.contentToString()}")
    }

    private fun mapRect(){
        val rectF = RectF(400f , 400f , 1000f, 800f)
        val rectF1 = RectF()

        val matrix = Matrix()
        matrix.setScale(0.5f , 1f)
        matrix.postSkew(1f, 0f)

        Log.e("Sion" , "mapRect: $rectF")
        matrix.mapRect(rectF1 , rectF)
        Log.e("Sion" , "mapRect: $rectF1")
    }

    private fun mapRadius(){
        val radius = 100f

        val matrix = Matrix()
        matrix.setScale(0.5f , 1f)
        Log.e("Sion" , "mapRadius: $radius")

        val result = matrix.mapRadius(radius)
        Log.e("Sion" , "mapRadius: $result")
    }

    private fun mapPoint(){
        val pts = floatArrayOf(0f , 0f , 80f ,100f , 400f , 300f)
        val des = FloatArray(6)
        val matrix = Matrix()
        matrix.setScale(0.5f , 1f)
        Log.e("Sion" , "before: src = ${pts.contentToString()}" )
        Log.e("Sion" , "before: des = ${des.contentToString()}" )
        matrix.mapPoints(des , 0, pts , 4, 1)
        Log.e("Sion" , "after: src = ${pts.contentToString()}")
        Log.e("Sion" , "after: des = ${des.contentToString()}")

    }

    private fun sincos(canvas: Canvas?){
        canvas?.also {
            it.save()

            val rectF = RectF(0f , 0f , 300f, 200f)
            val matrix = Matrix()
            matrix.preTranslate((width / 2).toFloat() , (height / 2).toFloat())
            mPaint.color = Color.BLACK
            it.setMatrix(matrix)
            it.drawRect(rectF , mPaint)

            val tmpMatrix = Matrix()
            tmpMatrix.setSinCos(1f , 0f)

//            matrix.preTranslate(150f, 100f)
            matrix.preConcat(tmpMatrix)
//            matrix.preTranslate(-150f, -100f)


            mPaint.color = Color.RED
            it.setMatrix(matrix)
            it.drawRect(rectF , mPaint)
            it.drawCircle(0f , 0f , 5f , mPaint)

            it.restore()
        }
    }

    private fun skew(canvas: Canvas?){
        canvas?.also {
            it.save()
            val rect = RectF(0f , 0f , 200f, 200f)
            val matrix = Matrix()

            matrix.preTranslate((width / 2 ).toFloat()  , (height / 2).toFloat())
            it.setMatrix(matrix)
            mPaint.setColor(Color.BLACK)
            it.drawPoint(0f , 0f , mPaint)
            it.drawRect(rect , mPaint)

            matrix.preSkew(1f , 0f , 200f ,200f)
            it.setMatrix(matrix)
            mPaint.color = Color.RED
            it.drawRect(rect , mPaint)

            it.drawCircle(0f , 0f , 5f , mPaint)
            it.restore()
        }
    }

    private fun scale(canvas: Canvas?){
        canvas?.also {
            it.save()
            val rect = RectF(0f , 0f , 400f, 400f)
            val matrix = Matrix()
            // 移动原点到View中心点
            matrix.preTranslate((width / 2).toFloat()  , (height / 2).toFloat())
            it.setMatrix(matrix)
            mPaint.setColor(Color.BLACK)
            it.drawPoint(0f , 0f , mPaint)
            it.drawRect(rect , mPaint)

            matrix.preScale(0.5f, 0.5f , 400f , 400f)
            it.setMatrix(matrix)
            mPaint.color = Color.RED
            it.drawRect(rect , mPaint)

            it.drawCircle(0f , 0f , 5f , mPaint)
            it.restore()
        }
    }
}