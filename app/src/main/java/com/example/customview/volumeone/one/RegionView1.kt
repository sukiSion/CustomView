package com.example.customview.volumeone.one

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class RegionView1: View {
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
        val paint = Paint().apply {
            color = Color.RED
            style = Paint.Style.FILL
        }

        val region = Region(
            10,
            10,
            200,
            100
        )

        region.union(
            Rect(
                10,
                100,
                50,
                300
            )
        )
        drawRegion(
            canvas,
            region,
            paint
        )

    }

    private fun drawRegion(canvas: Canvas? , region: Region , paint: Paint){
        val regionIterator = RegionIterator(region)
        val r = Rect()
        while (regionIterator.next(r)){
            canvas?.drawRect(
                r, paint
            )
        }
    }
}