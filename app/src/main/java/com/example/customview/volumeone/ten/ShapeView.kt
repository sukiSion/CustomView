package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Region
import android.graphics.Shader
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.ArcShape
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.PathShape
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class ShapeView: View {
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

    init {
        setLayerType(LAYER_TYPE_SOFTWARE , null)
    }

    private val rectShapeDrawable: ShapeDrawable by lazy {
        ShapeDrawable(RectShape()).apply {
            bounds = Rect(
                0,
                0,
                100,
                100
            )
            paint.shader = BitmapShader(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.dog
                ) ,
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT
            )
//            paint.style = Paint.Style.STROKE
            paint.color = Color.YELLOW
        }
    }

    private val ovalShapeDrawable: ShapeDrawable by lazy {
        ShapeDrawable(OvalShape()).apply {
            bounds = Rect(
                50,
                50,
                200,
                100
            )
            paint.color = Color.YELLOW
        }
    }

    private val arcShapeDrawable: ShapeDrawable by lazy {
        ShapeDrawable(ArcShape(0f , 300f)).apply {
            bounds = Rect(
                50,
                50,
                200,
                100
            )
            paint.color = Color.YELLOW
        }
    }

    private val roundRectDrawable: ShapeDrawable by lazy {
        ShapeDrawable(RoundRectShape(
            floatArrayOf(
                12f,
                12f,
                12f,
                12f,
                0f,
                0f,
                0f,
                0f
            ),
            RectF(
                6f,
                6f,
                6f,
                6f
            ),
            floatArrayOf(
                50f,
                12f,
                0f,
                0f,
                50f,
                12f,
                0f,
                0f
            )
        )).apply {
            bounds = Rect(
                50,
                50,
                200,
                100
            )
            paint.color = Color.YELLOW
        }
    }

    private val pathDrawable: ShapeDrawable by lazy {
        val path = Path().apply {
            moveTo(0f , 0f)
            lineTo(100f , 0f)
            lineTo(100f , 100f)
            lineTo(0f , 100f)
            close()
        }
        ShapeDrawable(PathShape(
            path,
            100f,
            200f
        )).apply {
            bounds = Rect(
                0,
                0,
                250,
                250,
            )
            paint.color = Color.YELLOW
        }
    }

    private val regionDrawable: ShapeDrawable by  lazy {
        val rect1 = Rect(0 , height / 2 - 25 , width , height / 2 + 25)
        val rect2 = Rect(width / 2 - 25 , 0 , width / 2 + 25 , height)

        val region1 = Region(rect1)
        val region2 = Region(rect2)

        region1.op(region2 , Region.Op.XOR)

        ShapeDrawable(RegionShape(region1)).apply {
            bounds = Rect(
                0,
                0,
                width,
                height
            )

            paint.color = Color.YELLOW
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            rectShapeDrawable.draw(it)
        }
    }


}