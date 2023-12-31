package com.example.customview.volumetwo.four

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.example.customview.R

class ConflictScrollView: ScrollView
{
    private var mDrawPointY = 0f
    private var mConflictHeight  = 0

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
        mConflictHeight = context?.resources?.getDimensionPixelSize(R.dimen.conflict_height) ?: 0
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return when(ev?.action){
            MotionEvent.ACTION_DOWN -> {
                mDrawPointY = ev.y
                false
            }
            MotionEvent.ACTION_MOVE -> {
                // 在textView内部不拦截使其传到TextView让TextView处理
                if(mDrawPointY < mConflictHeight){
                    false
                    // 在外部拦截让滑动布局处理
                }else{
                    true
                }
            }
            else -> {
                false
            }
        }
    }
}