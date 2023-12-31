package com.example.customview.volumetwo.five

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.lang.Exception

class MultiTouchView: View {
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

    private var haveSecondPoint = false
    private var point: PointF = PointF(0f , 0f)
    private val paint by lazy {
        Paint().apply {
            color = Color.WHITE
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
            textSize = 30f
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.also {
            // 获取当前活动的是第几跟手指
            val index = it.actionIndex
            when(it.actionMasked){
                MotionEvent.ACTION_POINTER_DOWN -> {
                    if(event.getPointerId(index) == 1){
                        haveSecondPoint = true
                        point.set(it.x , it.y)
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    try {
                        if(haveSecondPoint){
                            // 获取到第二根手指的位置
                            val pointIndex = it.findPointerIndex(1)
                            point.set(it.getX(pointIndex) , it.getY(pointIndex))
                        }
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }
                // 判断抬起的
                MotionEvent.ACTION_POINTER_UP -> {
                    if(it.getPointerId(index) == 1){
                        haveSecondPoint = false
                    }
                }
                MotionEvent.ACTION_UP -> {
                    haveSecondPoint = false
                }
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {

        canvas?.also {
            it.drawColor(Color.GREEN)

            if(haveSecondPoint){
                it.drawCircle(point.x , point.y , 50f , paint)
            }

            it.save()

            it.translate((width / 2).toFloat() , (height / 2).toFloat())
            it.drawText("追踪第二根按下的手指的位置" , 0f , 0f , paint)

            it.restore()
        }
    }
}