package com.example.customview.volumetwo.five

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView

class MulitDragImageView: AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var mLeft = 0
    private var mTop = 0
    private var mStartX = 0f
    private var mStartY = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.actionMasked){
            MotionEvent.ACTION_DOWN -> {
                mStartX = event.x
                mStartY = event.y
                mLeft = left
                mTop = top
            }
            MotionEvent.ACTION_MOVE -> {
                val index = event.findPointerIndex(0)
                if(index == -1){
                    return false
                }
                mLeft += (event.x - mStartX).toInt()
                mTop += (event.y -mStartY).toInt()
                this.layout(mLeft ,mTop , mLeft + width , mTop+ height)
            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return true
    }
}