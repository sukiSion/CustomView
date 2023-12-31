package com.example.customview.volumeone.eight

import android.graphics.Canvas
import android.view.View

fun Canvas.drawWithMode(view: View , draw: () -> Unit){
    val layerId = saveLayer(
        0f,
        0f,
        view.width.toFloat(),
        view.height.toFloat(),
        null
    )
    draw()
    restoreToCount(layerId)
}