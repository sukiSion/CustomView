package com.example.customview.volumetwo.eight

import android.graphics.Rect
import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlin.math.abs

class CoverFlowLayoutManager: LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    private var mTotalWidth = 0
    private val mItemRects by lazy {
        SparseArray<Rect>()
    }
    // 标识是否重新布局
    private val mHasAttachedItems by lazy {
         SparseArray<Boolean>()
    }
    private var mIntervalWidth:Int = 0
    private var mStartX = 0

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        recycler?.also {

            if(itemCount == 0) {
                detachAndScrapAttachedViews(it)
                return
            }
            mHasAttachedItems.clear()
            mItemRects.clear()

            detachAndScrapAttachedViews(it)

            val childView = it.getViewForPosition(0)
            measureChildWithMargins(childView , 0 , 0)
            val mItemWidth = getDecoratedMeasuredWidth(childView)
            val mItemHeight = getDecoratedMeasuredHeight(childView)
            mIntervalWidth = mItemWidth  / 2
            mStartX = width / 2 - mIntervalWidth
            val mVisiableCount = getHorizontalSpace() / mIntervalWidth + 1

            // 相对与上一个item相对距离
            var offsetX = 0
            for(i in 0 until itemCount){
//                val view = it.getViewForPosition(i)
//                addView(view)
//                measureChildWithMargins(view , 0 , 0)
//                val width = getDecoratedMeasuredWidth(view)
//                val height = getDecoratedMeasuredHeight(view)
//                layoutDecorated(view , 0 , offsetY , width , offsetY + height)

                val rect = Rect(mStartX + offsetX , 0 , mStartX + offsetX + mItemWidth ,
                    mItemHeight
                )
                mItemRects[i] = rect
                mHasAttachedItems[i] = false
                offsetX += mIntervalWidth
            }

            for(i in 0 until mVisiableCount){
                val rect = mItemRects[i]
                val view = it.getViewForPosition(i)
                addView(view)
                measureChildWithMargins(view , 0 , 0)
                layoutDecoratedWithMargins(view , rect.left , rect.top , rect.right , rect.bottom)
                handleChildView(view , rect.left - mStartX)
            }

            mTotalWidth = maxOf(offsetX , getHorizontalSpace())
        }
    }

    private fun getHorizontalSpace(): Int{
        return width - paddingStart - paddingEnd
    }

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        if(childCount <= 0){
            return dx
        }


        recycler?.apply {
            val travel = if(mSumDx + dx < 0){
                -mSumDx
            }else if(mSumDx+ dx > getMaxOffset()){
                getMaxOffset() - mSumDx
            } else{
                dx
            }

            mSumDx += travel
            val visiableRect = getVisiableArae()

            for(i in childCount - 1 downTo  0 ){
                getChildAt(i)?.also {
                    val position = getPosition(it)
                    val rect = mItemRects[position]

                    // 越界了，remove
                    if(!Rect.intersects(rect, visiableRect)){
                        removeAndRecycleView(it , this)
                        mHasAttachedItems[position] = false
                    }
                    // 没有越界，重新布局
                    else{
                        layoutDecoratedWithMargins(
                            it ,
                            rect.left - mSumDx,
                            rect.top,
                            rect.right - mSumDx,
                            rect.bottom
                        )
                        handleChildView(it , rect.left - mStartX - mSumDx)
                        mHasAttachedItems[position] = true
                    }

                }
            }

            val lastView = getChildAt(childCount - 1)
            val firstView = getChildAt(0)


            if(travel >= 0){
                firstView?.also {
                        firstView ->
                    val minPos = getPosition(firstView)
                    for(i in minPos until itemCount ) {
                        insertView(i , visiableRect , this , false)
                    }
                }
            }else{
                lastView?.also {
                        lastView ->
                    val maxPos = getPosition(lastView)
                    for(i in maxPos downTo 0){
                        insertView(i , visiableRect , this , true)
                    }
                }
            }

            return travel
        }
        return dx
    }


    private var mSumDx = 0



    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    private fun insertView(pos: Int , visiableRect: Rect , recycler: RecyclerView.Recycler , firstPos: Boolean){
        val rect = mItemRects[pos]
        if(Rect.intersects(visiableRect , rect) && !mHasAttachedItems[pos]){
            val child = recycler.getViewForPosition(pos)
            if(firstPos){
                addView(child , 0)
            }else{
                addView(child)
            }
            measureChildWithMargins(child , 0 , 0)
            layoutDecoratedWithMargins(
                child,
                rect.left - mSumDx,
                rect.top,
                rect.right - mSumDx,
                rect.bottom
            )
            handleChildView(child , rect.left - mStartX - mSumDx)
            mHasAttachedItems[pos] = true
        }
    }

    // 屏幕偏移后的矩形
    private fun getVisiableArae(): Rect{
        return Rect(
            paddingLeft + mSumDx,
            paddingTop,
            width - paddingEnd + mSumDx,
           height - paddingBottom
        )
    }

    fun getCenterPosition(): Int{
        var pos = mSumDx / mIntervalWidth
        val more = mSumDx % mIntervalWidth
        if(more > mIntervalWidth * 0.5f) pos++
        return pos
    }

    fun getFirstVisiablePosition(): Int{

        getChildAt(0)?.let {
            return getPosition(it)
        }

        return 0
    }

    private fun handleChildView(child: View , moveX: Int){
        val radio = computeScale(moveX)
        val rotationY = computeRotation(moveX)
        child.scaleX = radio
        child.scaleY = radio

        child.rotationY = rotationY
    }

    private fun computeScale(x: Int): Float{
        var scale = 1 - abs(x * 1.0f / (8f * mIntervalWidth))
        if(scale < 0) scale = 0f
        if (scale > 1) scale = 1f
        return scale
    }

    private val M_MAX_ROTATION_Y: Float = 30f
    private fun computeRotation(x: Int): Float{
        val rotationY = -M_MAX_ROTATION_Y * x / mIntervalWidth
        return if(abs(rotationY) > M_MAX_ROTATION_Y){
            if(rotationY > 0){
                M_MAX_ROTATION_Y
            }else{
                -M_MAX_ROTATION_Y
            }
        }else{
            rotationY
        }
    }

    private fun getMaxOffset(): Int{
        return (itemCount - 1 ) * mIntervalWidth
    }

    fun calculateDistance(velocity: Int , distance: Double): Double{
        val extra = mSumDx % mIntervalWidth
        val realDistance = if(velocity > 0){
            if(distance < mIntervalWidth){
                (mIntervalWidth - extra).toDouble()
            }else{
                distance - distance % mIntervalWidth + mIntervalWidth - extra
            }
        }else{
            if(distance < mIntervalWidth){
                extra.toDouble()
            }else{
                distance - distance % mIntervalWidth + extra
            }
        }
        return realDistance
    }

}