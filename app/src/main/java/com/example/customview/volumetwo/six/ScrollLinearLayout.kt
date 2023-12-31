package com.example.customview.volumetwo.six

import android.content.Context
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import android.widget.Scroller

class ScrollLinearLayout: LinearLayout {
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

    private lateinit var mScroller: Scroller

    private fun init(context: Context?){
        mScroller = Scroller(context , LinearInterpolator())
    }

    fun startScroller(startX: Int , dx: Int){
        mScroller.startScroll(startX , 0 , dx , 0)
//        invalidate()
    }

    override fun computeScroll() {
        super.computeScroll()
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.currX , mScroller.currY)
        }
        invalidate()
    }
}