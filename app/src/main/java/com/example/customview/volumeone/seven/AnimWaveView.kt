package com.example.customview.volumeone.seven

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class AnimWaveView : View{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        startAnimator()
    }
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

    private val paint:Paint
    private val path: Path
    private val mItemWaveLength = 600f
    private var dx: Float = 0f

    init {
        paint = Paint().apply {
            color = Color.GREEN
            style = Paint.Style.FILL
        }
        path = Path()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.reset()
        val originY = 300f
        val halfWaveLen = mItemWaveLength / 2
        path.moveTo(-mItemWaveLength + dx , originY)
        var i = -mItemWaveLength
        while (i <= width  +  mItemWaveLength){
            path.rQuadTo(
                halfWaveLen / 2 , -100f,
                halfWaveLen  , 0f
            )
            path.rQuadTo(
                halfWaveLen / 2 , 100f,
                halfWaveLen ,0f
            )
            i += mItemWaveLength
        }
        path.apply {
            lineTo(width.toFloat()  ,  height.toFloat())
            lineTo(0f ,  height.toFloat())
            close()
        }

        canvas?.drawPath(
            path,
            paint
        )
    }

    private fun startAnimator(){
        ValueAnimator.ofFloat(0f , mItemWaveLength).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener {
                dx =  it.animatedValue  as Float
                postInvalidate()
            }
        }.start()
    }
}