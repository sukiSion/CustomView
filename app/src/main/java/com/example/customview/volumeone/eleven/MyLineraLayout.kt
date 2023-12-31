package com.example.customview.volumeone.eleven

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class MyLineraLayout: ViewGroup {
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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)

        var height = 0
        var width = 0

        for(i in 0 until childCount){
            getChildAt(i).let {
                measureChild(it , widthMeasureSpec , heightMeasureSpec)
                val marginLayoutParams = it.layoutParams as MarginLayoutParams
                height += it.measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin
                width = maxOf(width , it.measuredWidth + marginLayoutParams.marginStart + marginLayoutParams.marginEnd)
            }
        }

        setMeasuredDimension(
            if(measureWidthMode == MeasureSpec.EXACTLY){
                measureWidth
            }else{
                width
            },
            if(measureHeightMode == MeasureSpec.EXACTLY){
                measureHeight
            }else{
                height
            }
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var top = 0
        for(index in 0 until childCount){
            getChildAt(index).let {
                val marginLayoutParams = it.layoutParams as MarginLayoutParams
                it.layout(0  , top + marginLayoutParams.topMargin , it.measuredWidth , top + it.measuredHeight + marginLayoutParams.topMargin)
                top += it.measuredHeight + marginLayoutParams.topMargin
            }
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context , attrs)
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )

    }
}