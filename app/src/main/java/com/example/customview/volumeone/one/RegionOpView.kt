package com.example.customview.volumeone.one

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Region
import android.graphics.RegionIterator
import android.util.AttributeSet
import android.view.View

class RegionOpView : View {
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

        val rect1 = Rect(
            100,
            100,
            400,
            200
        )

        val rect2 = Rect(
            200,
            0,
            300,
            300
        )

        val paint = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 2f
        }

        canvas?.drawRect(
            rect1,
            paint
        )

        canvas?.drawRect(
            rect2,
            paint
        )

        val region1 = Region(rect1)
        val region2 = Region(rect2)

        val region = Region()

        region.op(region1 , region2 , Region.Op.XOR)

        val paint1 = Paint().apply {
            color = Color.GREEN
            style = Paint.Style.FILL
        }

        drawRegion(
            canvas,
            region,
            paint1
        )
    }

    private fun drawRegion(canvas: Canvas? , region: Region , paint: Paint){

        val regionIterator = RegionIterator(region)
        val r = Rect()
        while (regionIterator.next(r)){
            canvas?.drawRect(
                r,
                paint
            )
        }
    }
}