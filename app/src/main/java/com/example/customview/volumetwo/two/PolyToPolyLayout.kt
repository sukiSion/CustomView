package com.example.customview.volumetwo.two

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout

class PolyToPolyLayout: LinearLayout{
    constructor(context: Context?) : super(context){
        initData()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initData()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initData()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        initData()
    }


    companion object{
//        private const val sTransHeight = 100
        private const val sFoldNum = 8
    }

    private lateinit var mBitmap: Bitmap
    private lateinit var mMatrixs: Array<Matrix>
    private var mFoldWidth: Float = 0f
    private var mFactor = 0.8f
    private lateinit var mSolidPaint:Paint
    private lateinit var mShadowPaint: Paint
    private lateinit var mShadowGradientShader: LinearGradient
    private var mWidth = 0
    private var mHeight = 0


    private fun initData(){
        mMatrixs = Array(8){
            Matrix()
        }
        mShadowPaint = Paint()
        mSolidPaint = Paint()
    }

    private fun updateFlod(){
        mWidth = measuredWidth
        mHeight = measuredHeight
        mFoldWidth = mWidth.toFloat() / sFoldNum
        val alpha = 255 * (1 - mFactor)
        mSolidPaint.also {
            it.color = Color.argb((alpha * 0.8f).toInt() ,  0 , 0 ,0)
        }
        mShadowPaint.alpha = alpha.toInt()
        mShadowGradientShader = LinearGradient(
            0f, 0f,
            mFoldWidth , 0f,
            Color.BLACK,
            Color.TRANSPARENT,
            Shader.TileMode.CLAMP
        ).also {
            mShadowPaint.shader = it

        }
        val foldedItemWidth = mWidth * mFactor / sFoldNum
        val depth = Math.sqrt((mFoldWidth * mFoldWidth - foldedItemWidth * foldedItemWidth).toDouble())
        for(i in 0 until sFoldNum){
            val isEvent = i % 2 == 0
            val sLeft = mFoldWidth * i
            val sRight = mFoldWidth * (i + 1)
            val transWidth = (1 - mFactor) * mWidth
            val src = floatArrayOf(
                sLeft , 0f,
                sRight , 0f,
                sLeft, mHeight.toFloat(),
                sRight , mHeight.toFloat()
            )
            val dst = floatArrayOf(
                foldedItemWidth * i + transWidth, if(isEvent) 0f else depth.toFloat(),
                foldedItemWidth * (i + 1) + transWidth, if(isEvent) depth.toFloat() else 0f,
                foldedItemWidth * i + transWidth, if(isEvent) mHeight -  depth.toFloat()else mHeight.toFloat() ,
                foldedItemWidth * (i + 1) + transWidth, if(isEvent) mHeight.toFloat() else mHeight - depth.toFloat()
            )
            mMatrixs[i].setPolyToPoly(src , 0 , dst , 0, src.size / 2)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        updateFlod()
    }

    override fun dispatchDraw(canvas: Canvas) {
        for(i in 0 until sFoldNum){
            canvas.save()
            val rect = RectF((mFoldWidth * i), 0f , (mFoldWidth * (i + 1)), mHeight.toFloat())
            canvas.setMatrix(mMatrixs[i])
            canvas.clipRect(rect)
            super.dispatchDraw(canvas)
            canvas.translate((mFoldWidth * i), 0f)
            if(i % 2 == 0){
                canvas.drawRect(0f , 0f , mFoldWidth, mHeight.toFloat(), mSolidPaint)
            }else{
                canvas.drawRect(0f , 0f , mFoldWidth, mHeight.toFloat(), mShadowPaint)
            }
            canvas.restore()
        }
    }


    fun setFactor(factor: Float){
        mFactor = factor
        updateFlod()
        invalidate()
    }
}