package com.example.customview.volumeone.five

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.util.Log
import android.view.View

class PathAnimView : View{
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

        canvas?.translate(150f , 150f)

        val paint = Paint().apply {
            color = Color.BLACK
            strokeWidth = 8f
            style = Paint.Style.STROKE

        }

//        val path = Path().apply {
//            lineTo(0f , 100f)
//            lineTo(100f , 100f)
//            lineTo(100f, 0f)
//        }

        // 关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE , null)


        val path = Path().apply {
            addRect(-50f , -50f , 50f , 50f , Path.Direction.CW)
//            addPath(Path().apply {
//                addRect(-100f, -100f, 100f, 100f, Path.Direction.CW)
//            })
//            addPath(Path().apply {
//                addRect(-120f , -120f , 120f , 120f, Path.Direction.CW )
//            })
        }

        val desPath = Path().apply {
            lineTo(10f , 100f)
        }

//        canvas?.drawPath(path , paint)

        val pathMeasure1 = PathMeasure(path , false)
//        val pathMeasure2 = PathMeasure(path , true)

        pathMeasure1.getSegment(0f , 150f , desPath , false)

        canvas?.drawPath(desPath , paint)

//        do {
//            Log.e("forceClosed" , "false: ${pathMeasure1.length}")
//        }while (pathMeasure1.nextContour())
//        Log.e("forceClosed" , "true: ${pathMeasure2.length}")


    }
}