package com.example.customview.volumeone.eleven

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

class FlowLayout : ViewGroup {
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
        var lineWidth = 0
        var lineHeight = 0


        for(index in 0 until childCount){
            getChildAt(index).let {

                measureChild(it , widthMeasureSpec , heightMeasureSpec)

                val layoutParams = it.layoutParams as MarginLayoutParams
                val childWidth = it.measuredWidth + layoutParams.marginStart + layoutParams.marginEnd
                val childHeight = it.measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin

                if(lineWidth + childWidth > measureWidth){

                    height += childHeight
                    width = max(lineWidth , width)

                    lineHeight = childHeight
                    lineWidth = childWidth
                }else{
                    lineWidth += childWidth
                    lineHeight = max(childHeight , lineHeight)
                }

                if(index == childCount - 1){
                    height += lineHeight
                    width = max(width , lineWidth)
                }
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
        var left = 0
        var lineWidth = 0
        var lineHeight = 0

        for(index in 0 until childCount){
            getChildAt(index).let {
                val layoutParams = it.layoutParams as MarginLayoutParams
                val childWidth = it.measuredWidth + layoutParams.marginStart + layoutParams.marginEnd
                val childHeight = it.measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin

                if(childWidth + lineWidth > measuredWidth) {
                    top += lineHeight
                    left = 0
                    lineHeight = childHeight
                    lineWidth = childWidth
                }else{
                    lineHeight = max(lineHeight , childHeight)
                    lineWidth += childWidth
                }

                val sc = layoutParams.marginStart + left
                val tc = layoutParams.topMargin + top
                val ec = it.measuredWidth + sc
                val bc = it.measuredHeight + tc

                it.layout(
                    sc,
                    tc,
                    ec,
                    bc
                )

                left += childWidth
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