package com.example.customview.volumeone.seven

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class NormalGestureTrackView: View {

    private val paint:Paint
    private val path:Path

    init {
        paint = Paint().apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 5f
        }
        path = Path()
    }

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

    private var preX: Float = 0f
    private var preY: Float = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
           when(action){
               MotionEvent.ACTION_DOWN -> {
                   path.moveTo(
                       x , y
                   )
                   preX = x
                   preY = y
                   return true
               }
               MotionEvent.ACTION_MOVE -> {
                   val currentX = (preX + x) / 2
                   val currentY = (preY + y) / 2
                   path.quadTo(
                       preX,
                       preY,
                       currentX,
                       currentY
                   )
                   preX = x
                   preY = y
                   postInvalidate()
               }
               else -> {

               }
           }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawColor(Color.WHITE)
            drawPath(path , paint)
        }
    }
}