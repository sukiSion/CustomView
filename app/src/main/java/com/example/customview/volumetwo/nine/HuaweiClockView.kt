package com.example.customview.volumetwo.nine

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Xfermode
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.graphics.scale
import com.example.customview.R
import java.util.Calendar

class HuaweiClockView: View {
    constructor(context: Context?) : super(context){
        init(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : this(context , attrs , 0 , 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs , defStyleAttr , 0)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        val typedArray = context?.obtainStyledAttributes(attrs , R.styleable.HuaweiClockView)
        typedArray?.also {
            mTextSize = it.getDimensionPixelSize(R.styleable.HuaweiClockView_timeTextSize , dipToPx(40f).toInt())
            mTextColor = it.getColor(R.styleable.HuaweiClockView_timeTextColor , Color.RED)
            it.recycle()
        }
        init(context)
    }

    companion object{
        const val SECOND = 1000
        const val MINUTE = 60 * SECOND
    }

    private var mAngle = 0f
    private lateinit var mClockAnimator: ValueAnimator
    private lateinit var mClockMaskBitmap: Bitmap
    private lateinit var mClockBitmap: Bitmap
    private lateinit var mPaint: Paint
    private lateinit var mXfermode: Xfermode
    private lateinit var mClockRectF: RectF
    private var mInitAngle = 0f
    private lateinit var mCalendar: Calendar

    private var mDigitalTimeTextStartX = 0f
    private var mDigitaltimeTextStartY = 0f

    private var mTextSize = 0
    private var mTextColor = 0

    private fun init(context: Context?){
        mClockBitmap = BitmapFactory.decodeResource(resources , R.drawable.clock_1).let {
            it.scale(it.width / 2 , it.height / 2 )
        }
        mClockMaskBitmap = BitmapFactory.decodeResource(resources , R.drawable.clock_mask).let {
            it.scale(it.width / 2 , it.height / 2 )
        }
        mXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
        mPaint = Paint().also {
            it.isAntiAlias = true
        }
        mClockRectF = RectF()
        mCalendar = Calendar.getInstance()

        val defaultText = "00:00:00"
        val timeTextRectF = Rect()
        mPaint.textSize = mTextSize.toFloat()
        mPaint.getTextBounds(defaultText , 0 , defaultText.length  , timeTextRectF)
        val fontMetrices = mPaint.fontMetrics
        mDigitalTimeTextStartX = (mClockBitmap.width / 2 - timeTextRectF.width() / 2).toFloat()
        mDigitaltimeTextStartY = mClockBitmap.height / 2 +  ((fontMetrices.bottom -  fontMetrices.top) / 2 - fontMetrices.bottom)

    }

    private fun dipToPx( dip: Float): Float {
        return context.resources.displayMetrics.density * dip + 0.5f
    }


    fun performAnimator(){
        if(!this::mClockAnimator.isInitialized) {
            mClockAnimator = ValueAnimator.ofFloat(0f, 360f).apply {
                addUpdateListener {
                    mAngle = it.animatedValue as Float  +  mInitAngle
                    this@HuaweiClockView.invalidate()
                }
                duration = MINUTE.toLong()
                interpolator = LinearInterpolator()
                repeatCount = ValueAnimator.INFINITE
                addListener(object : AnimatorListener{
                    override fun onAnimationCancel(animation: Animator) {
                    }

                    override fun onAnimationEnd(animation: Animator) {
                    }

                    override fun onAnimationRepeat(animation: Animator) {
                    }

                    override fun onAnimationStart(animation: Animator) {
                        mCalendar.timeInMillis = System.currentTimeMillis()
                        mInitAngle = (mCalendar.get(Calendar.SECOND) * (360 / 60)).toFloat()
                    }
                })
            }
        }
        if(!mClockAnimator.isRunning) {
            mClockAnimator.start()
        }
    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val withSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mClockBitmap.width , mClockBitmap.height)
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mClockBitmap.width , heightSize)
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(withSize , mClockBitmap.height)
        }else{
            setMeasuredDimension(withSize ,  heightSize)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mClockRectF.set(RectF(0f , 0f , mClockBitmap.width.toFloat(), mClockBitmap.height.toFloat()))

        // 如果图片具有透明度应该使用saveLayer
        canvas?.apply {
            saveLayer(mClockRectF  , mPaint)

            saveLayer(mClockRectF , mPaint)
            // 先画的目标图像
            // 先把画布旋转对应的角度然后将遮罩画上去
            rotate(mAngle , (mClockBitmap.width / 2).toFloat(), (mClockBitmap.height / 2).toFloat())
            drawBitmap(mClockMaskBitmap , 0f ,0f , mPaint)
            restore()

            mPaint.xfermode = mXfermode
            // 后画的为源图像
            drawBitmap(mClockBitmap , 0f , 0f , mPaint)
            mPaint.xfermode = null

            restore()
            updateTimeText(canvas)
        }


    }

    private fun updateTimeText(canvas: Canvas){
        val cutrrentTimeMillis = System.currentTimeMillis()
        mCalendar.timeInMillis = cutrrentTimeMillis
        val mLastDigitalTimeStr = String.format("%02d:%02d:%02d" , mCalendar.get(Calendar.HOUR) , mCalendar.get(Calendar.MINUTE) , mCalendar.get(Calendar.SECOND))
        mPaint.color = mTextColor
        canvas.drawText(mLastDigitalTimeStr , mDigitalTimeTextStartX , mDigitaltimeTextStartY , mPaint)

    }

}