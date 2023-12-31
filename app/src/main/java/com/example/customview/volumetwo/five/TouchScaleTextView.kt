package com.example.customview.volumetwo.five

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

class TouchScaleTextView: AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var mode = 0
    // 缩放比例
    private var mOldDist = 0f
    private var mTextSize = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(mTextSize == 0f){
            mTextSize = textSize
        }
        when(event?.actionMasked){
            MotionEvent.ACTION_DOWN -> {
                mOldDist = 0f
                mode = 1
            }
            MotionEvent.ACTION_UP -> {
                mode = 0
            }
            MotionEvent.ACTION_MOVE -> {
                if(mode >= 2){
                    val newDist = spacing(event)
                    if(Math.abs(newDist - mOldDist) > 50){
                        zoom(newDist / mOldDist)
                        mOldDist = newDist
                    }
                }
            }
            MotionEvent.ACTION_POINTER_UP -> {
                mode -= 1
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                mOldDist = spacing(event)
                mode += 1
            }
        }

        return true
    }

    private fun zoom(f: Float){
        mTextSize *= f
        setTextSize(TypedValue.COMPLEX_UNIT_PX , mTextSize)
    }

    private fun spacing(event: MotionEvent): Float{
        val x = (event.getX(0) - event.getX(1)).toDouble()
        val y = (event.getY(0) - event.getY(1)).toDouble()

        return Math.sqrt(Math.pow(x , 2.0) + Math.pow(y , 2.0)).toFloat()
    }
}