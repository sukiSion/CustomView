package com.example.customview.volumetwo.six

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Scroller
import com.example.customview.R

class ToggleButton: ViewGroup {
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

    private var mSliderWidth = 0
    private var mScrollerWidth = 0
    private lateinit var mSliderView: ImageView
    private lateinit var mBgView: ImageView
    private lateinit var mScroller: Scroller
    private var mIsOpen = false
    private var mLastX = 0f

    private fun init(context: Context?){
        setBackgroundResource(R.drawable.background)
        mBgView = ImageView(context).also {
            it.setBackgroundResource(R.drawable.background)
            addView(it)
        }

        mSliderView = ImageView(context).also {
            it.setBackgroundResource(R.drawable.slide)
            it.setOnClickListener {
                if(mIsOpen){
                    mScroller.startScroll(-mScrollerWidth , 0 , mScrollerWidth , 0 , 500)
                }else{
                    mScroller.startScroll(0 , 0 , -mScrollerWidth , 0 , 500)
                }
                mIsOpen = !mIsOpen
                invalidate()
            }
            addView(it)
        }


        mScroller = Scroller(context)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val bgDrawable = resources.getDrawable(R.drawable.background , null)
        setMeasuredDimension(bgDrawable.intrinsicWidth , bgDrawable.intrinsicHeight)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        mLastX = ev?.x ?: 0f
        if(ev?.action == MotionEvent.ACTION_MOVE){
            return true
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.also {
            val x = it.x
            when(it.action){
                MotionEvent.ACTION_DOWN -> {
                    if(!mScroller.isFinished){
                        // 强行结束
                        mScroller.abortAnimation()
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    val deltaX = mLastX - x
                    // 防止左越界
                    if(deltaX + scrollX > 0){
                        scrollTo(0 , 0)
                        return true
                    }
                    else if(deltaX + scrollX < -mSliderWidth){
                        scrollTo(-mSliderWidth , 0)
                        return true
                    }
                    scrollBy(deltaX.toInt() , 0)
                }
                MotionEvent.ACTION_UP -> {
                    smoothScroll()
                }
            }
            mLastX = x
        }
        return super.onTouchEvent(event)
    }

    private fun smoothScroll(){
        val bound = -mSliderWidth / 4
        val deltaX =
        if(scrollX < bound){
            if(!mIsOpen){
                mIsOpen = true
            }
            -mSliderWidth - scrollX
        }else if(scrollX >= bound){
            if(mIsOpen){
                mIsOpen = false
            }
            -scrollX
        }else{
            0
        }
        mScroller.startScroll(scrollX , 0 , deltaX , 0 , 500)
        invalidate()
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        mSliderWidth = measuredWidth / 2
        mScrollerWidth = measuredWidth / 2
        mSliderView.layout(
            0 , 0 , mSliderWidth , measuredHeight
        )
        mBgView.layout(
            0 , 0 , measuredWidth , measuredHeight
        )
    }

    override fun computeScroll() {
        super.computeScroll()
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.currX , mScroller.currY)
            invalidate()
        }
    }
}