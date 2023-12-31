package com.example.customview.volumetwo.seven

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.customview.R

class CustomItemDecoration(val context: Context): RecyclerView.ItemDecoration() {

    private val mPaint: Paint = Paint().also {
        it.color = Color.GREEN
    }
    private val mBitmap: Bitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.message_icon ,
        BitmapFactory.Options()
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.also {
//            it.top =  1
            it.bottom =  1
            it.left = 200
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val layoutManager = parent.layoutManager
        for (i in 0 until parent.childCount){
            val child = parent.getChildAt(i)
            val left:Int = layoutManager?.getLeftDecorationWidth(child) ?: 0
//            c.drawCircle(
//                (left / 2).toFloat(),
//                (child.top + child.height / 2).toFloat(),
//                20F,
//                mPaint
//            )
            val  index = parent.getChildAdapterPosition(child)
            if(index  %  5  ==  0){
                c.drawBitmap(
                    mBitmap,
                    (left - mBitmap.width / 2).toFloat(),
                    (child.top ).toFloat(),
                    mPaint
                )
            }
        }
        val temp = parent.getChildAt(0)
        val linearGradient = LinearGradient(
            (parent.width / 2).toFloat(),
            0f,
            (parent.width / 2).toFloat(),
            (temp.height * 3).toFloat(),
            0xff0000ff.toInt(), 0x00ffffff.toInt(),
            Shader.TileMode.CLAMP
        )
        mPaint.shader =  linearGradient
        c.drawRect(RectF(
            0f , 0f, parent.width.toFloat() , (temp.height * 3).toFloat(),
        )  , mPaint)
    }
}