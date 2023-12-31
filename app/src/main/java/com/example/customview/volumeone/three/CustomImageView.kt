package com.example.customview.volumeone.three

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.imageview.ShapeableImageView

class CustomImageView: ShapeableImageView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )


    fun getScaleSize(){
        scaleX = 5f
    }
}