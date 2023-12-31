package com.example.customview.volumeone.three

import android.animation.TypeEvaluator

class ReverseEvaluator: TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int?, endValue: Int?): Int {
        return (endValue!! - fraction * (endValue - startValue!!)).toInt()
    }
}