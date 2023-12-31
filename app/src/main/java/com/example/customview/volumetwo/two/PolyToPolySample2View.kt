package com.example.customview.volumetwo.two

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class PolyToPolySample2View: View {
    constructor(context: Context?) : super(context){
        initData()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initData()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(
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
        private const val sTransHeight = 100
        private const val sFlodNum = 8
    }

    private lateinit var mBitmap: Bitmap
    private lateinit var mMatrix: Matrix
    private var mFlodWidth: Int = 0
    private var mFactor = 0.8f

    private fun initData(){
        mBitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.sample
        )
        mFlodWidth = mBitmap.width / sFlodNum
        mMatrix = Matrix()

        val foldItemWidth = mBitmap.width * mFactor / sFlodNum
        val depth = Math.sqrt((mFlodWidth * mFlodWidth - foldItemWidth * foldItemWidth).toDouble()).toFloat()

        val src = floatArrayOf(
            mFlodWidth.toFloat() , 0f ,
            (2 * mFlodWidth).toFloat(), 0f ,
            mFlodWidth.toFloat() , mBitmap.height.toFloat(),
            (2 * mFlodWidth).toFloat() , mBitmap.height.toFloat()
        )

        val dst = floatArrayOf(
            foldItemWidth , depth,
            foldItemWidth * 2 , 0f,
            foldItemWidth , depth + mBitmap.height,
            foldItemWidth * 2 , mBitmap.height.toFloat()
        )

        mMatrix.setPolyToPoly(src , 0 , dst , 0 , src.size / 2)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        val rect = RectF(mFlodWidth.toFloat(), 0f , (mFlodWidth * 2).toFloat(), height.toFloat())
        canvas.setMatrix(mMatrix)
        canvas.clipRect(rect )
        canvas.drawBitmap(mBitmap , 0f , 0f, null)

        canvas.restore()
    }
}