package com.example.customview.volumetwo.four

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class CustomTextView: AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.e("textView"  ,  "dispatchTouchEvent(event: MotionEvent?)" )
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("textView"  ,  "onTouchEvent(event: MotionEvent?)" )
        parent.requestDisallowInterceptTouchEvent(true)
//        return super.onTouchEvent(event)
        return true
    }
}