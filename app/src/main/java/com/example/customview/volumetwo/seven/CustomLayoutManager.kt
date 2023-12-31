package com.example.customview.volumetwo.seven

import android.graphics.Rect
import android.graphics.RectF
import android.util.SparseArray
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class CustomLayoutManager: LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    private var mTotalHeight = 0
    private val mItemRects by lazy {
        SparseArray<Rect>()
    }
    // 标识是否重新布局
    private val mHasAttachedItems by lazy {
         SparseArray<Boolean>()
    }

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

            val mVisiableCount = getVertialSpace() / mItemHeight + 1



            var offsetY = 0
            for(i in 0 until itemCount){
//                val view = it.getViewForPosition(i)
//                addView(view)
//                measureChildWithMargins(view , 0 , 0)
//                val width = getDecoratedMeasuredWidth(view)
//                val height = getDecoratedMeasuredHeight(view)
//                layoutDecorated(view , 0 , offsetY , width , offsetY + height)

                val rect = Rect(0 , offsetY , mItemWidth ,
                    offsetY + mItemHeight
                )
                mItemRects[i] = rect
                mHasAttachedItems[i] = false
                offsetY += mItemHeight
            }

            for(i in 0 until mVisiableCount){
                val rect = mItemRects[i]
                val view = it.getViewForPosition(i)
                addView(view)
                measureChildWithMargins(view , 0 , 0)
                layoutDecorated(view , rect.left , rect.top , rect.right , rect.bottom)
            }

            mTotalHeight = maxOf(offsetY , getVertialSpace())
        }
    }

    private fun getVertialSpace(): Int{
        return height - paddingTop - paddingBottom
    }

    override fun canScrollVertically(): Boolean {
        return true
    }


    private var mSumDy = 0



    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {

        if(childCount <= 0){
            return dy
        }

        var topOrBottom = false

        recycler?.apply {
            val travel = if(mSumDy + dy < 0){
                topOrBottom = true
                -mSumDy
            }else if(mSumDy+ dy > mTotalHeight - getVertialSpace()){
                topOrBottom = true
                mTotalHeight - getVertialSpace() - mSumDy
            } else{
                dy
            }

            mSumDy += travel
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
                        layoutDecorated(
                            it ,
                            rect.left,
                            rect.top - mSumDy,
                            rect.right,
                            rect.bottom - mSumDy
                        )
                        if(!topOrBottom){
                            it.rotationY = it.rotationY + 1
                        }
                        mHasAttachedItems[position] = true
                    }

//                    // 向上滑动，回收上面的item
//                    if(travel > 0){
//                        if(getDecoratedBottom(it) - travel < paddingTop){
//                            removeAndRecycleView(it , this)
//                        }
//                        // 向下滑动，回收下面的item
//                    }else if(travel < 0){
//                        if(getDecoratedTop(it) - travel > height - paddingBottom){
//                            removeAndRecycleView(it , this)
//                        }
//                    }
                }
            }

            val lastView = getChildAt(childCount - 1)
            val firstView = getChildAt(0)
//            detachAndScrapAttachedViews(this)


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

//            val visiableRect = getVisiableArae(travel)
//            if(travel >= 0){
//                getChildAt(childCount - 1)?.also {
//                    lastView ->
//                    val minPos = getPosition(lastView) + 1
//
//                    for(i in minPos until itemCount){
//                        val rect = mItemRects[i]
//                        if(Rect.intersects(visiableRect , rect)){
//                            val child = getViewForPosition(i)
//                            addView(child)
//                            measureChildWithMargins(child , 0 , 0)
//                            layoutDecorated(
//                                child,
//                                rect.left,
//                                rect.top - mSumDy,
//                                rect.right,
//                                rect.bottom - mSumDy
//                            )
//                        }else{
//                            break
//                        }
//                    }
//                }
//            }else{
//                getChildAt(0)?.also {
//                    firstView ->
//                    val maxPos = getPosition(firstView) - 1
//                    for(i in maxPos downTo 0){
//                        val rect = mItemRects[i]
//                        if(Rect.intersects(visiableRect , rect)){
//                            val child = getViewForPosition(i)
//                            addView(child , 0)
//                            measureChildWithMargins(child , 0 , 0)
//                            layoutDecoratedWithMargins(
//                                child,
//                                rect.left,
//                                rect.top - mSumDy,
//                                rect.right,
//                                rect.bottom - mSumDy
//                            )
//                        }else{
//                            break
//                        }
//                    }
//                }
//            }
//
//            mSumDy += travel
//            offsetChildrenVertical(-travel)
            return travel
        }
        return dy

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
                rect.left ,
                rect.top - mSumDy,
                rect.right,
                rect.bottom - mSumDy
            )
            child.rotationY = child.rotationY + 1
            mHasAttachedItems[pos] = true
        }
    }

    // 屏幕偏移后的矩形
    private fun getVisiableArae(): Rect{
        return Rect(
            paddingLeft ,
            paddingTop + mSumDy,
            width - paddingRight,
            height - paddingBottom + mSumDy
        )
    }

    private fun getVisiableArae(travel: Int): Rect{
        return Rect(
            paddingLeft ,
            paddingTop + mSumDy + travel,
            width + paddingLeft,
            getVertialSpace() + mSumDy + travel
        )
    }
}