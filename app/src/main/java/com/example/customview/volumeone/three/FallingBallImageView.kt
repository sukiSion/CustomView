package com.example.customview.volumeone.three

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import com.google.android.material.imageview.ShapeableImageView

class FallingBallImageView: ShapeableImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setFallingPos(pos: Point){
        layout(
            pos.x,
            pos.y,
            width + pos.x,
            height + pos.y
        )
    }
}