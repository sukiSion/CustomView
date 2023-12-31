package com.example.customview.volumetwo.eight

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerView: RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val flingUtil:FlingUtil

    init {
        isChildrenDrawingOrderEnabled = true
        flingUtil = FlingUtil(context)
    }

    private fun getCoverFlowLayoutManager():CoverFlowLayoutManager{
        return  layoutManager as CoverFlowLayoutManager
    }

    override fun getChildDrawingOrder(childCount: Int, i: Int): Int {
//        return super.getChildDrawingOrder(childCount, i)
        // 计算中间View在屏幕上的位置
        val center = getCoverFlowLayoutManager().getCenterPosition() - getCoverFlowLayoutManager().getFirstVisiablePosition();
        return if(i == center){
             childCount - 1
        }else if(i < center){
            i
        }else{
            center + childCount - 1 - i
        }
    }

    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        var flingX = (velocityX * 0.4f).toInt()
        val coverFlowLayoutManager = getCoverFlowLayoutManager()
        val distance = flingUtil.getSplineFlingDistance(flingX)
        val newDistance = coverFlowLayoutManager.calculateDistance(velocityX , distance)
        val fixVelocityX = flingUtil.getVelocity(newDistance)
        flingX = if(velocityX > 0){
            fixVelocityX
        }else{
            -fixVelocityX
        }
        return super.fling(flingX, velocityY)
    }



}