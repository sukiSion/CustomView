package com.example.customview.volumetwo.two

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.scale
import com.example.customview.R

class MatrixImageView: AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val mCamera = Camera()
    private val mMatrix = Matrix()
    private val mPaint = Paint().run {
        alpha = 100
        this
    }
    private val mBitmap by lazy {
        val bitmap = BitmapFactory.decodeResource(context.resources , R.drawable.p1)
        bitmap.scale(width , height)
    }
    private var mTranslateX = 0f
    private var mTranslateY = 0f
    private var mTranslateZ = 0f
    private var mRotateX = 0f
    private var mProgress = 0f


    fun translateX(x:Float){
//        mTranslateX = x
        mProgress = x
        postInvalidate()
    }
    fun translateY(y:Float){
//        mTranslateY = y
        mProgress = y
        postInvalidate()
    }
    fun translateZ(z:Float){
        mTranslateZ = z
        postInvalidate()
    }

    fun rotateX(x:Float){
        mRotateX = x
        postInvalidate()
    }

    fun rotate(x:Float){
        mProgress = x
        postInvalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        onDrawWithMatrix(canvas){
            super.onDraw(canvas)
        }
    }

    private fun onDrawWithMatrix(canvas: Canvas? , onDraw: (canvas: Canvas?) -> Unit){
        val centerX: Float= (width / 2).toFloat()
        val centerY: Float = (height / 2).toFloat()
        canvas?.save()
        canvas?.drawBitmap(mBitmap , 0f , 0f , mPaint)
        mMatrix.reset()
//        mMatrix.setTranslate(0f , mProgress)
        mMatrix.setRotate(mProgress)
        mMatrix.preTranslate(-centerX , -centerY)
        mMatrix.postTranslate(centerX , centerY)
        canvas?.setMatrix(mMatrix)
        onDraw(canvas)
        canvas?.restore()
    }


    private fun OnDrawWithCamera(canvas: Canvas? , onDraw: (canvas: Canvas?) -> Unit){
        mCamera.save()
        mCamera.translate(mTranslateX  , mTranslateY , mTranslateZ)
        mCamera.rotateX(mRotateX)
        mCamera.getMatrix(mMatrix)
        Log.e("Sion" , mMatrix.toShortString())
        mCamera.restore()

        canvas?.save()
        canvas?.setMatrix(mMatrix)
        onDraw(canvas)
        canvas?.restore()
    }
}