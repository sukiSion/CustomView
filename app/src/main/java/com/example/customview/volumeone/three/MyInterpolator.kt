package com.example.customview.volumeone.three

import android.animation.TimeInterpolator

class MyInterpolator: TimeInterpolator {
    override fun getInterpolation(input: Float): Float {
        return 1 - input
    }
}