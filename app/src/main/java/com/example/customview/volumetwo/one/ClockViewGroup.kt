package com.example.customview.volumetwo.one

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.animation.BounceInterpolator
import android.widget.LinearLayout

class ClockViewGroup : LinearLayout {
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

    private var mCenterX = 0f
    private var mCenterY = 0f
    private var mCanvasRotateX = 0f
    private var mCanvasRotateY = 0f
    private val mMatrixCanvas = Matrix()
    private val mCamera = Camera()
    private val MAX_ROTATE_DEGREE = 20
    // 复位动画
    private var mSteayAnim: ValueAnimator? = null

    private fun startNewSteayAnim(){
        val propertyNameRotateX = "mCanvasRotateX"
        val propertyNameRotateY = "mCanvasRotateY"
        val holderRotateX = PropertyValuesHolder.ofFloat(
            propertyNameRotateX , mCanvasRotateX , 0f
        )
        val holderRotateY = PropertyValuesHolder.ofFloat(
            propertyNameRotateY , mCanvasRotateY , 0f
        )
        mSteayAnim = ValueAnimator.ofPropertyValuesHolder(holderRotateX , holderRotateY).apply {
            duration = 1000
            interpolator = BounceInterpolator()
            addUpdateListener {
                mCanvasRotateX = it.getAnimatedValue(propertyNameRotateX) as Float
                mCanvasRotateY = it.getAnimatedValue(propertyNameRotateY) as Float
                postInvalidate()
            }
        }.also {
            it.start()
        }
    }

    private fun cancelSteayAnimIfNeed(){
        mSteayAnim?.also {
            if(it.isStarted || it.isRunning){
                it.cancel()
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCenterX = (w / 2).toFloat()
        mCenterY = (h / 2).toFloat()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        // 矩阵复位
        mMatrixCanvas.reset()

        // 使用矩阵来获取子控件需要进行操作
        mCamera.save()
        mCamera.rotateX(mCanvasRotateX)
        mCamera.rotateY(mCanvasRotateY)
        mCamera.getMatrix(mMatrixCanvas)
        mCamera.restore()

        // 移动到中心点
        mMatrixCanvas.preTranslate(-mCenterX , -mCenterY)
        mMatrixCanvas.postTranslate(mCenterX , mCenterY)

        canvas?.save()
        canvas?.setMatrix(mMatrixCanvas)
        super.dispatchDraw(canvas)
        canvas?.restore()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.also {
            val x = it.x
            val y = it.y
            val action = it.actionMasked
            when(action){
                MotionEvent.ACTION_DOWN -> {
                    // 响应手势前取消动画
                    cancelSteayAnimIfNeed()
                    rotateCanvasWhenMove(x , y)
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    rotateCanvasWhenMove(x , y)
                    return true
                }
                MotionEvent.ACTION_UP -> {
                    startNewSteayAnim()
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }

    // 根据移动距离计算旋转角度函数
    private fun rotateCanvasWhenMove(x: Float , y: Float){
        var percentX = (x - mCenterX) / (width / 2)
        var percentY = (y - mCenterY) / (height / 2)
        if(percentX > 1f){
            percentX = 1f
        }else if(percentX < -1f){
            percentX = -1f
        }

        if(percentY > 1f){
            percentY = 1f
        }else if(percentY < -1f){
            percentY = -1f
        }

        // 注意旋转方向
        mCanvasRotateY = percentX * MAX_ROTATE_DEGREE
        mCanvasRotateX = -(percentY * MAX_ROTATE_DEGREE)

        postInvalidate()
    }


}