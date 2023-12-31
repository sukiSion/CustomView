package com.example.customview.volumetwo.three

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

class CustomViewGroup : ViewGroup{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    // 必须要重写
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var top = 0
        for(i in 0 until childCount){
            val child = getChildAt(i)
            // 注意，在onLayout中还不能拿到子控件的真正宽高
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            // 注意是使用子控件的布局函数，而不是布局控件的布局函数
            child.layout(0 , top , childWidth , top+ childHeight)
            top += childHeight
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureChildren(widthMeasureSpec , heightMeasureSpec)
        var height = 0
        var maxWidth = 0
        for (i in 0 until childCount){
            val child = getChildAt(i)
            val childWidth = child.measuredWidth
            maxWidth = max(maxWidth , childWidth)
            val childHeight = child.measuredHeight
            height += childHeight
        }
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(
                maxWidth,
                height
            )
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(
                maxWidth,
                widthSize
            )
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(
                heightSize,
                height
            )
        }else{
            setMeasuredDimension(
                widthSize,
                heightSize
            )
        }
    }

}