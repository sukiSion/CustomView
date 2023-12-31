package com.example.customview.volumeone.three

import android.animation.TypeEvaluator
import android.util.Log

class MyEvaluator : TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int?, endValue: Int?): Int{
        return (200 + startValue!! + fraction * (endValue!! - startValue)).toInt()
    }
}