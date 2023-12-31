package com.example.customview.volumetwo.two

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class PolyToPolyView: View{

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
    private lateinit var mMatrixs: Array<Matrix>
    private var mFlodWidth: Int = 0
    private var mFactor = 0.8f
    private lateinit var mSolidPaint:Paint
    private lateinit var mShadowPaint: Paint
    private lateinit var mShadowGradientShader: LinearGradient


    private fun initData(){
        mBitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.sample
        )
        mFlodWidth = mBitmap.width / sFlodNum
        val foldItemWidth = mBitmap.width * mFactor / sFlodNum
        val depth = Math.sqrt((mFlodWidth * mFlodWidth - foldItemWidth * foldItemWidth).toDouble()).toFloat()
        mMatrixs = Array(8){ Matrix() }

        for(index in 0 until sFlodNum){

            val isEven = index % 2 == 0

            val isLeft = (mFlodWidth * index).toFloat()
            val isRight = (mFlodWidth * (index + 1)).toFloat()

            val src = floatArrayOf(
                isLeft, 0f ,
                isRight , 0f,
                isLeft, mBitmap.height.toFloat(),
                isRight , mBitmap.height.toFloat()
            )

            val dst = floatArrayOf(
                foldItemWidth * index , if(isEven) 0f else depth,
                foldItemWidth * (index + 1), if(isEven) depth else 0f,
                foldItemWidth * index, if(isEven) mBitmap.height.toFloat() else mBitmap.height + depth ,
                foldItemWidth * (index + 1) , if(isEven) mBitmap.height + depth else mBitmap.height.toFloat()
            )

            mMatrixs[index].setPolyToPoly(src , 0 , dst , 0 , src.size / 2)
        }
        mShadowGradientShader = LinearGradient(
            0f, 0f,
            mFlodWidth.toFloat(), 0f,
            Color.BLACK , Color.TRANSPARENT,
            Shader.TileMode.CLAMP
        )
        val alpha = (255 * (1 - mFactor)).toInt()
        mSolidPaint = Paint().also {
            it.color = Color.argb((alpha * 0.8f).toInt() , 0, 0 , 0)
        }
        mShadowPaint = Paint().also {
            it.alpha = alpha
            it.shader = mShadowGradientShader
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for(index in 0 until sFlodNum){
            // 每一次循环都需要回复画布
            canvas.save()

            val rect = RectF((mFlodWidth * index).toFloat(), 0f , (mFlodWidth * (index + 1)).toFloat(), height.toFloat())
            canvas.setMatrix(mMatrixs[index])
            canvas.clipRect(rect)
            canvas.drawBitmap(mBitmap , 0f , 0f , null)

            mShadowPaint.shader = mShadowGradientShader

            canvas.translate((mFlodWidth * index).toFloat(), 0f)
            if(index % 2 == 0){
                canvas.drawRect(
                    0f, 0f ,
                    mFlodWidth.toFloat(), mBitmap.height.toFloat(),
                    mSolidPaint
                )
            }else{
                canvas.drawRect(
                    0f, 0f ,
                    mFlodWidth.toFloat(), mBitmap.height.toFloat(),
                    mShadowPaint
                )
            }

            canvas.restore()
        }
    }


}