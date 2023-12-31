package com.example.customview.volumetwo.nine

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import kotlin.math.min

class RingView: View {
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

    private lateinit var mPaint: Paint
    private var DEFAULT_DIMENSION = 0f

    private var mRadius = 0f
    private var mCenterX = 0f
    private var mCenterY = 0f
    private lateinit var mCircleRect: RectF
    private lateinit var mRingDataList: ArrayList<RingBean>
    private var mAnimatedValue = 120f
    private lateinit var mProgressAnimator: ValueAnimator


    private fun init(context: Context?){
        mPaint = Paint()
        DEFAULT_DIMENSION = dipToPx(50f)
        mCircleRect = RectF()
        mRingDataList = ArrayList()
        mProgressAnimator = ValueAnimator.ofFloat(0f , 360f).apply {
            interpolator = AccelerateInterpolator()
            addUpdateListener {
                mAnimatedValue = it.animatedValue as Float
                this@RingView.invalidate()
            }
        }
    }

    fun setProgressAnimation(duration: Long){
        mProgressAnimator.duration = duration
        if(mProgressAnimator.isRunning){
            mProgressAnimator.cancel()
            mProgressAnimator.start()
        }else{
            mProgressAnimator.start()
        }
    }

    fun setDatas(ringDatas: ArrayList<RingBean>){
        mRingDataList.clear()
        if(ringDatas.isEmpty()){
            return
        }
        mRingDataList.addAll(ringDatas)
    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val sizeLength = min(w , h)
        mRadius = (sizeLength / 2).toFloat()
        mCenterX = (w / 2).toFloat()
        mCenterY = (h / 2).toFloat()

        val strokeWidth = mRadius * 0.3f
        mPaint.strokeWidth = strokeWidth

        val rectSize = mRadius - strokeWidth / 2
        mCircleRect.set(-rectSize , -rectSize , rectSize , rectSize)
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

    private fun dipToPx(dp: Float):Float{
        return context.resources.displayMetrics.density * dp + 0.5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            save()
            translate(mCenterX , mCenterY)
            mPaint.color = Color.RED
            mPaint.style = Paint.Style.STROKE
//            drawArc(mCircleRect , 0f , 360f , false , mPaint)/\
            var startAngle = 0f
            for(ringBean in mRingDataList){
                val sweepAngle = ringBean.percentage
                val drawAngle = min(sweepAngle , mAnimatedValue - startAngle)
                if(drawAngle > 0){
                    mPaint.color = ringBean.color
                    drawArc(mCircleRect , startAngle , drawAngle , false , mPaint)
                }
                startAngle += ringBean.percentage
            }
            restore()
        }
    }


}