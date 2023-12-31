package com.example.customview.volumetwo.seven

import android.graphics.Canvas
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections
import kotlin.math.abs

class CustomItemTouchHelperCallBack(
    val mDatas: MutableList<String>,
    val mAdapter: RecyclerViewAdapter
): ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(
            dragFlags , swipeFlags
        )
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPosition = viewHolder.absoluteAdapterPosition
        val toPosition = target.absoluteAdapterPosition
        Collections.swap(mDatas , fromPosition , toPosition)
        mAdapter.notifyItemMoved(fromPosition , toPosition)
        return true
    }



    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        viewHolder.absoluteAdapterPosition.also {
            mDatas.removeAt(it)
            mAdapter.notifyItemRemoved(it)
        }
        Log.d("Sion" , "onSwiped direction: ${direction}")
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)

        when(actionState){
            ItemTouchHelper.ACTION_STATE_SWIPE -> {
                viewHolder?.also {
                    viewHolder ->
                    val bgColor = viewHolder.itemView.context.resources.getColor(android.R.color.holo_orange_light)
                    viewHolder.itemView.setBackgroundColor(bgColor)
                }
            }
            ItemTouchHelper.ACTION_STATE_DRAG -> {
                viewHolder?.also {
                        viewHolder ->
                    val bgColor = viewHolder.itemView.context.resources.getColor(android.R.color.holo_blue_bright)
                    viewHolder.itemView.setBackgroundColor(bgColor)
                }
            }
            ItemTouchHelper.ACTION_STATE_IDLE -> {
                // 注：变成闲置状态后viewHolder为null
                viewHolder?.also {
                        viewHolder ->
                    val bgColor = viewHolder.itemView.context.resources.getColor(android.R.color.white)
                    viewHolder.itemView.setBackgroundColor(bgColor)
                }
            }
        }
        Log.d("Sion" , "actionState: ${actionState}")
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        val bgColor = viewHolder.itemView.context.resources.getColor(android.R.color.white)
        viewHolder.itemView.setBackgroundColor(bgColor)
        viewHolder.itemView.alpha = 1f
        viewHolder.itemView.scaleX = 1f
        viewHolder.itemView.scaleY = 1f
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        when(actionState){
            ItemTouchHelper.ACTION_STATE_SWIPE -> {
                val alpha = 1 - abs(dX) / viewHolder.itemView.width
                viewHolder.itemView.also {
                    it.alpha = alpha
                    it.scaleX = alpha
                    it.scaleY = alpha
                }
            }
        }
    }

    // Swipe超过多少百分比会消失
    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return super.getSwipeThreshold(viewHolder)
    }

    // Swipe逃逸速度，虽然没有超过对应的百分比位置，但是只要达到了对应的逃逸速度也会消失
    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return super.getSwipeEscapeVelocity(defaultValue)
    }

    // 阻尼系数，最大滑动速度
    override fun getSwipeVelocityThreshold(defaultValue: Float): Float {
        return super.getSwipeVelocityThreshold(defaultValue)
    }

    // 针对Drag , 当前target对应的item是否允许拖动
    override fun canDropOver(
        recyclerView: RecyclerView,
        current: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return super.canDropOver(recyclerView, current, target)
    }

    // 针对Drag , 可以增大重叠判定范围，使Drag事件更容易发生
    override fun getBoundingBoxMargin(): Int {
        return super.getBoundingBoxMargin()
    }

    // 针对Drag, 当前移动超过多少百分比可以调用onMove
    // 注：不是触发onDrag , 而是已经Drag了在此函数条件基础上调用onMove
    override fun getMoveThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return super.getMoveThreshold(viewHolder)
    }

    // 针对Drag, 获取下面的viewHolder
    // 一般不用处理直接调用
    override fun chooseDropTarget(
        selected: RecyclerView.ViewHolder,
        dropTargets: MutableList<RecyclerView.ViewHolder>,
        curX: Int,
        curY: Int
    ): RecyclerView.ViewHolder {
        return super.chooseDropTarget(selected, dropTargets, curX, curY)
    }

    // 与ItemDecoration的onDrawOver类似，在onChildDraw后调用，能画到viewHolder的ItemView的最上层
    // 可以画一些蒙层之类的
    override fun onChildDrawOver(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder?,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    // 手指离开回到指定位置持续的时间
    // 注：这里是回到，而不是我们进行拖曳交换或者侧滑消失所需要的时间
    override fun getAnimationDuration(
        recyclerView: RecyclerView,
        animationType: Int,
        animateDx: Float,
        animateDy: Float
    ): Long {
        return super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy)
    }

    // 针对Drag, 拖曳到边界时recyclerView会滑动，此函数用于获取滑动距离
    // 通常不用我们重写，直接调用即可
    override fun interpolateOutOfBoundsScroll(
        recyclerView: RecyclerView,
        viewSize: Int,
        viewSizeOutOfBounds: Int,
        totalSize: Int,
        msSinceStartScroll: Long
    ): Int {
        return super.interpolateOutOfBoundsScroll(
            recyclerView,
            viewSize,
            viewSizeOutOfBounds,
            totalSize,
            msSinceStartScroll
        )
    }

}