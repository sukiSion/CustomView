package com.example.customview.volumeone.seven

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class ShinnerTextView: AppCompatTextView {
    constructor(context: Context) : super(context){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init()
    }

    private lateinit var linearGradient: LinearGradient

    private fun init(){
        val length = paint.measureText("${text}")
        createLinearGradient(length)
        startAnim(length)
    }

    override fun onDraw(canvas: Canvas?) {
        val matrix = Matrix().apply {
            setTranslate(mDx , 0f)
        }
        linearGradient.setLocalMatrix(matrix)
        paint.shader = linearGradient
        super.onDraw(canvas)
    }

    private var mDx: Float = 0f

    private fun startAnim(length: Float){
        val animator = ValueAnimator.ofFloat(0f , 2 * length).apply {
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            duration = 2000
            addUpdateListener {
                mDx = it.animatedValue as Float
                postInvalidate()
            }
        }.start()
    }

    private fun createLinearGradient(length: Float) {
        linearGradient = LinearGradient(
            -length,
            0f,
            0f,
            0f,
            intArrayOf(
                currentTextColor,
                (0xff00ff00).toInt(),
                currentTextColor
            ),
            floatArrayOf(
                0f,
                0.5f,
                1f
            ),
            Shader.TileMode.CLAMP
        )
    }


}