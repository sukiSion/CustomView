package com.example.customview.volumetwo.eight

import android.content.Context
import android.graphics.Canvas
import android.util.TypedValue
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.customview.volumetwo.eight.library.ItemTouchHelperExtension
import kotlin.math.abs
import kotlin.math.max

class QQItemTouchHelpCallBack(
    private val context: Context,
    private val mDatas: List<String>,
    private val adapter: QQListAdapter
): QQListItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = ItemTouchHelper.START
        return makeMovementFlags(0 , swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

    private val mMaxWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160f, context.getResources().getDisplayMetrics())

    private fun getViewHolder(viewHolder: ViewHolder): QQListAdapter.QQListViewHolder{
        return viewHolder as QQListAdapter.QQListViewHolder
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
//        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//        getDefaultUIUtil().onDraw(c , recyclerView , (viewHolder as QQListAdapter.QQListViewHolder).itemText ,
//            dX, dY, actionState, isCurrentlyActive)
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            getViewHolder(viewHolder).itemText.translationX = dX
        }
    }
}