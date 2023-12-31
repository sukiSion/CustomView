package com.example.customview.volumetwo.four

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout

class CustomConstraintLayout1: ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("CustomConstraintLayout1" , "onTouchEvent(event: MotionEvent?)")
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("CustomConstraintLayout1" , "dispatchTouchEvent(ev: MotionEvent?)")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("CustomConstraintLayout1" , "onInterceptTouchEvent(ev: MotionEvent?)")
        return super.onInterceptTouchEvent(ev)
    }
}