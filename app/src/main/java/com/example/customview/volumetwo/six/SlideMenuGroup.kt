package com.example.customview.volumetwo.six

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MotionEventCompat
import androidx.customview.widget.ViewDragHelper
import androidx.customview.widget.ViewDragHelper.Callback
import com.example.customview.R
import kotlin.math.min

class SlideMenuGroup: FrameLayout {
    constructor(context: Context) : super(context){
        initView()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        initView()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initView()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        initView()
    }

    private lateinit var mMainView: View
    private lateinit var mViewDragHelper: ViewDragHelper
    private lateinit var mMenuView: View
    private var mMenuViewWidth = 500

    private fun initView(){
        if(this::mViewDragHelper.isInitialized){
            return
        }
        mViewDragHelper = ViewDragHelper.create(
            this,
            object : Callback(){
                override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                    return child == mMainView
                }

                override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                    if(left > 0){
                        return min(left , mMenuViewWidth)
                    }
                    return 0
                }

                override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                    super.onViewReleased(releasedChild, xvel, yvel)
                    if(mMainView.left < mMenuViewWidth / 2){
                        mViewDragHelper.smoothSlideViewTo(
                            mMainView ,
                            0 , 0
                        )
                    }else{
                        mViewDragHelper.smoothSlideViewTo(
                            mMainView, mMenuViewWidth, 0
                        )
                    }
                    invalidate()
                }

                override fun onViewPositionChanged(
                    changedView: View,
                    left: Int,
                    top: Int,
                    dx: Int,
                    dy: Int
                ) {
                    super.onViewPositionChanged(changedView, left, top, dx, dy)
                    val percent = mMainView.left / mMenuViewWidth.toFloat()
                    excuteAnimation(percent)
                }
            }
        )
    }

    private fun excuteAnimation(percent: Float){
        mMenuView.scaleX = 0.5f + 0.5f * percent
        mMenuView.scaleY = 0.5f + 0.5f * percent



        mMainView.scaleX = 1 - percent * 0.2f
        mMainView.scaleY = 1 - percent * 0.2f

        mMainView.translationY = (mMainView.height * (percent * 0.2f) ) / 2
        mMenuView.translationX = mMenuViewWidth / 2 * (percent - 1)
    }

    fun setView(mainView: View , mainLayoutParams:  LayoutParams){
        mMainView = mainView
        addView(mainView , mainLayoutParams)
    }

    fun setView(mainView: View , mainLayoutParams: LayoutParams , menuView: View , menuLayoutParams: LayoutParams){

        mMenuView = menuView
        addView(menuView , menuLayoutParams)
        mMenuViewWidth = menuLayoutParams.width


        setView(mainView , mainLayoutParams)
    }

    override fun computeScroll() {
        super.computeScroll()
        if(this::mViewDragHelper.isInitialized && mViewDragHelper.continueSettling(true)){
            invalidate()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return ev?.let { mViewDragHelper.shouldInterceptTouchEvent(it) } ?: super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            mViewDragHelper.processTouchEvent(it)
        }
        return true
    }

    fun changeMainViewText(text: String){
        mMainView.findViewById<TextView>(R.id.tv_main).text = text
        mViewDragHelper.smoothSlideViewTo(mMainView , 0 , 0)
        postInvalidateOnAnimation()
    }

}