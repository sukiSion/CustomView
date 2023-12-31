package com.example.customview.volumetwo.one

import android.content.Context
import android.graphics.Bitmap
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

class CameraImageView: AppCompatImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val mPaint: Paint
    private val mBitmap: Bitmap
    private var mProgress: Float = 0f
    private val camera = Camera()
    private val matrix = Matrix()

    init {
        mBitmap = BitmapFactory.decodeResource(resources , R.drawable.cat1)
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.alpha = 100
    }

    fun setProgress(progress:Float){
        mProgress = progress
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        camera.save()
        canvas?.save()

        Log.e("Sion" , "Location X: ${camera.locationX} Y: ${camera.locationY} Z:  ${camera.locationZ}")

        canvas?.drawBitmap(mBitmap , 0f , 0f   ,  mPaint)
//        camera.rotateY(mProgress)
//        camera.rotateX(mProgress)
        camera.rotateZ(mProgress)
//        camera.translate(mProgress ,  0f  ,  0f)
//        camera.translate(0f ,  mProgress  ,  0f)
//        camera.translate(0f , 0f , mProgress)

        val centerX = (width /  2 / 72).toFloat()
        val centerY = (height / 2 / 72).toFloat()
        camera.setLocation(centerX , -centerY , camera.locationZ)



        // 获取变换后的矩阵
        camera.getMatrix(matrix)
        // 调整中心点位置
//        val centerX1 = (width / 2).toFloat()
//        val centerY1=  (height / 2).toFloat()
//        matrix.preTranslate(-centerX1 , -centerY1)
//        matrix.postTranslate(centerX1 , centerY1)


        canvas?.setMatrix(matrix)
//        camera.applyToCanvas(canvas)
        camera.restore()
        super.onDraw(canvas)

        canvas?.restore()
    }


}