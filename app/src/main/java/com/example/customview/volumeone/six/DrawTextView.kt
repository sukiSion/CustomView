package com.example.customview.volumeone.six

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class DrawTextView: View {
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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawText2(canvas)
    }

    private fun drawText2(canvas: Canvas?){
          
    }

    private fun drawText1(canvas: Canvas?){
        val text = "harvic\'s blog"
        val top = 200f
        val baseLineX = 0f
        val paint = Paint().run {
            textSize = 120f
            textAlign = Paint.Align.LEFT
            this
        }

        paint.color = Color.YELLOW
        canvas?.drawLine(baseLineX , top , 3000f, top , paint)

        val fontMetrics = paint.fontMetrics
        val baseLineY = top - fontMetrics.top

        paint.color = Color.RED
        canvas?.drawLine(baseLineX , baseLineY , 3000f , baseLineY , paint)

        paint.color = Color.GREEN
        canvas?.drawText(
            text,
            baseLineX, baseLineY,
            paint
        )
    }

    private fun drawText(canvas: Canvas?){
        val baseLineX = 0f
        val baseLineY = 200f

        val paint = Paint().run {
            color = Color.RED
            this
        }

        canvas?.drawLine(baseLineX , baseLineY , 3000f , baseLineY , paint)

        paint.textSize = 120f
        paint.textAlign = Paint.Align.LEFT


        val fontMetrics = paint.fontMetrics
        val ascent = baseLineY + fontMetrics.ascent
        val descent = baseLineY + fontMetrics.descent
        val top = baseLineY + fontMetrics.top
        val bottom = baseLineY + fontMetrics.bottom

        paint.color = Color.BLUE
        canvas?.drawLine(baseLineX , top , 3000f , top, paint)

        paint.color = Color.GREEN
        canvas?.drawLine(baseLineX , ascent , 3000f , ascent , paint)


        paint.color = Color.YELLOW
        canvas?.drawLine(baseLineX , descent , 3000f , descent , paint)

        paint.color = Color.CYAN
        canvas?.drawLine(baseLineX , bottom , 3000f , bottom, paint)

        paint.color = Color.GRAY
        canvas?.drawRect(
            RectF(
                baseLineX ,
                fontMetrics.ascent + baseLineY,
                paint.measureText("harvic\'s blog"),
                baseLineY + fontMetrics.bottom
            ),
            paint
        )
        val minRect = Rect()
        paint.getTextBounds("harvic\'s blog" , 0 , "harvic\'s blog".length , minRect)
        paint.color = Color.BLACK
        val trueMinRect = RectF(
            minRect.left.toFloat(),
            minRect.top + baseLineY,
            minRect.right.toFloat(),
            minRect.bottom + baseLineY
        )
        canvas?.drawRect(
            trueMinRect,
            paint
        )
        paint.color = Color.GREEN
        canvas?.drawText("harvic\'s blog" , baseLineX , baseLineY , paint)
    }

}
