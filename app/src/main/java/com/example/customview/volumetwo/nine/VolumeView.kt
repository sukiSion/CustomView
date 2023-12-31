package com.example.customview.volumetwo.nine

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class VolumeView: View {
    constructor(context: Context?) : super(context){
        init(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        init(context)
    }

    private var DEFAULT_DIMENSION = 0f
    private lateinit var mPaint: Paint
    private val mBackgroundColor = 0x60000000
    private var mBorderWidth = 0f
    private val mVolumeBgColor = (0x80000000).toInt()
    private val mVolumeColor = Color.WHITE

    private var mRadius = 0f
    private var mVolumeRadius = 0f
    private var mCenterX = 0f
    private var mCenterY = 0f
    private lateinit var mVolumeRect: RectF

    private var mVolumeNum = 0
    private val mMaxVolume = 10
    private val mUniteDegree = 360 / mMaxVolume
    private var mIsVolumeUp = true
    private var mAnimatedDegree = 0f

    private fun init(context: Context?){
        DEFAULT_DIMENSION = dipToPx(150f)
        mBorderWidth = dipToPx(8f)
        mPaint = Paint().apply {
            isAntiAlias = true
        }
        mVolumeRect = RectF()
    }



    fun volunmeUp(){
        mIsVolumeUp = true
        if(mVolumeNum < mMaxVolume){
            mVolumeNum ++
            startAnim()
        }
    }

    fun volumeDown(){
        mIsVolumeUp = false
        if(mVolumeNum > 0){
            mVolumeNum--
            startAnim()
        }
    }

    private fun startAnim(){
        ValueAnimator.ofFloat(0f , mUniteDegree.toFloat()).apply {
            duration = 300
            addUpdateListener {
                mAnimatedDegree = it.animatedValue as Float
                invalidate()
            }
        }.start()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mRadius = (min(w , h) / 2).toFloat()
        mVolumeRadius = mRadius - mBorderWidth
        mCenterX = (w / 2).toFloat()
        mCenterY = (h / 2).toFloat()
        mVolumeRect.set(
            -mVolumeRadius , -mVolumeRadius, mVolumeRadius , mVolumeRadius
        )
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val withSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(DEFAULT_DIMENSION.toInt() , DEFAULT_DIMENSION.toInt())
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(DEFAULT_DIMENSION.toInt() , heightSize)
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(withSize , DEFAULT_DIMENSION.toInt()
            )
        }else{
            setMeasuredDimension(withSize ,  heightSize)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            save()
            translate(mCenterX , mCenterY)
            mPaint.apply {
                color = mBackgroundColor
                style = Paint.Style.FILL
            }
            drawCircle(0f , 0f , mRadius , mPaint)

            mPaint.apply {
                color = mVolumeBgColor
                style = Paint.Style.STROKE
                strokeWidth = mBorderWidth
            }
            drawCircle(0f, 0f , mVolumeRadius , mPaint)

            mPaint.color = mVolumeColor
            if(mIsVolumeUp){
                val num = if(mVolumeNum - 1 > 0) mVolumeNum - 1 else 0
                drawArc(mVolumeRect , -90f , num * mUniteDegree + mAnimatedDegree , false , mPaint)
            }else{
                val num = mVolumeNum + 1
                drawArc(mVolumeRect , -90f , num * mUniteDegree - mAnimatedDegree , false , mPaint)
            }

            restore()
        }
    }

    private fun dipToPx(dp: Float): Float{
        return context.resources.displayMetrics.density * dp + 0.5f;
    }
}