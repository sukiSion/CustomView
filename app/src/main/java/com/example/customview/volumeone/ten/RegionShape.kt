package com.example.customview.volumeone.ten

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Region
import android.graphics.RegionIterator
import android.graphics.drawable.shapes.Shape

class RegionShape(val region: Region): Shape() {

    override fun draw(canvas: Canvas?, paint: Paint?) {
        val iter = RegionIterator(region)
        val r = Rect()
        while (iter.next(r)){
            canvas?.let {
                canvas ->
                paint?.also {
                    paint ->
                    canvas.drawRect(r , paint)
                }
            }
        }
    }
}