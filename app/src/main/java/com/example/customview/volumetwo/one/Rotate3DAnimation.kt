package com.example.customview.volumetwo.one

import android.graphics.Camera
import android.view.animation.Animation
import android.view.animation.Transformation

class Rotate3DAnimation(
    val mFromDegrees: Float,
    val mEndDegrees: Float,
    val mReverse: Boolean
) : Animation() {

    private var mCenterX = 0f
    private var mCenterY = 0f
    private lateinit var mCamera: Camera
    private val mDepthZ = 400f

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        mCenterX = (width / 2).toFloat()
        mCenterY = (height / 2).toFloat()
        mCamera = Camera()
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {

        val degrees = mFromDegrees + (mEndDegrees - mFromDegrees) * interpolatedTime
        mCamera.save()
        val z = if(mReverse){
            mDepthZ * interpolatedTime
        }else{
            mDepthZ * (1 - interpolatedTime)
        }
        val matrix = t.matrix
        mCamera.translate(0f , 0f , z)

        mCamera.rotateY(degrees)
        mCamera.getMatrix(matrix)
        mCamera.restore()

        matrix.preTranslate(-mCenterX , -mCenterY)
        matrix.postTranslate(mCenterX , mCenterY)

        super.applyTransformation(interpolatedTime, t)
    }
}