package com.example.customview.volumetwo.six

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.customview.widget.ViewDragHelper
import com.example.customview.R

class DragLayout : LinearLayout {

    private lateinit var mViewDragHelper: ViewDragHelper
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

    private fun init(context: Context?){
        mViewDragHelper = ViewDragHelper.create(
            this,
            1.0f,
            object : ViewDragHelper.Callback(){
                override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                    return child.id == R.id.tv1 || child.id == R.id.tv2
                }

                override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                    return left
                }

                override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                    return top
                }

                override fun getViewVerticalDragRange(child: View): Int {
                    return 1
                }

                override fun getViewHorizontalDragRange(child: View): Int {
                    return 1
                }

                override fun onEdgeDragStarted(edgeFlags: Int, pointerId: Int) {
                    super.onEdgeDragStarted(edgeFlags, pointerId)
                    if(edgeFlags == ViewDragHelper.EDGE_RIGHT){
                        mViewDragHelper.captureChildView(findViewById(R.id.tv3) , pointerId)
                    }
                    Log.e("Sion" , "onEdgeDragStarted edgeFlags: ${edgeFlags}")
                }

                override fun onEdgeLock(edgeFlags: Int): Boolean {
                    Log.e("Sion" , "onEdgeLock edgeFlags: ${edgeFlags}")
                    if(edgeFlags == ViewDragHelper.EDGE_LEFT || edgeFlags == ViewDragHelper.EDGE_RIGHT){
                        return true
                    }
                    return false
                }

                override fun onEdgeTouched(edgeFlags: Int, pointerId: Int) {
                    super.onEdgeTouched(edgeFlags, pointerId)
                    Log.e("Sion" , "onEdgeTouched edgeFlags: ${edgeFlags}")
                }

                override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                    if(releasedChild.id == R.id.tv2){
                        val tv1 = findViewById<TextView>(R.id.tv1)
//                        mViewDragHelper.smoothSlideViewTo(releasedChild , tv1.left , tv1.top)
                        mViewDragHelper.settleCapturedViewAt(tv1.left , tv1.top)
                        invalidate()
                    }
                    super.onViewReleased(releasedChild, xvel, yvel)
                }

            }
        ).also {
            it.setEdgeTrackingEnabled(
                ViewDragHelper.EDGE_ALL
            )
        }
    }

    override fun computeScroll() {
        super.computeScroll()
        if(mViewDragHelper.continueSettling(true)){
            invalidate()
        }
    }




    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return ev?.let { mViewDragHelper.shouldInterceptTouchEvent(it) } ?: super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("Sion" , "DragLayout onTouchEvent ${event?.action} ")

        event?.let {
            mViewDragHelper.processTouchEvent(event)
        }
        return true
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("Sion" , "DragLayout dispatchTouchEvent ${ev?.action} ")
        return super.dispatchTouchEvent(ev)
    }
}