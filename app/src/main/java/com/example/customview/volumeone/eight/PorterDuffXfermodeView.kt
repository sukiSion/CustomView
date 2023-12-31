package com.example.customview.volumeone.eight

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class PorterDuffXfermodeView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initParam()
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

    private lateinit var mPaint: Paint
    private lateinit var desBitmap: Bitmap
    private lateinit var srcBitmap: Bitmap
    private val bitmapWidth = 200
    private val bitmapHeight = 200


    private fun initParam(){
        // 禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE , null)
        desBitmap = makeDst()
        srcBitmap = makeSrc()
        mPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        drawDogPictureCorner1(canvas)
        canvas?.drawColor(Color.GREEN)
        canvas?.drawWithMode(
            this
        ){
            canvas.apply {
                drawBitmap(
                    desBitmap,
                    0f,
                    0f,
                    mPaint
                )
                mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
                drawBitmap(
                    srcBitmap,
                    desBitmap.width.toFloat() / 2,
                    desBitmap.height.toFloat()/ 2,
                    mPaint
                )
                mPaint.xfermode = null
            }
        }

    }

    private fun drawDogPictureCorner1(
        canvas: Canvas?
    ){
        canvas?.drawWithMode(this) {
            val dogBitmap = BitmapFactory.decodeResource(
                resources,
                R.drawable.dog
            )
            canvas.apply {
                drawBitmap(
                    dogBitmap,
                    20f,
                    20f,
                    mPaint
                )
                val bitmap  = Bitmap.createBitmap(
                    dogBitmap.width,
                    dogBitmap.height,
                    Bitmap.Config.ARGB_8888
                ).apply {
                    val canvas1 = Canvas(this)
                    canvas1.drawRoundRect(
                        0f,
                        0f,
                        this.width.toFloat(),
                        this.height.toFloat(),
                        50f,
                        50f,
                        mPaint
                    )
                }
                mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

                drawBitmap(
                    bitmap,
                    20f,
                    20f,
                    mPaint
                )
                mPaint.xfermode = null

            }
        }
    }

    private fun drawDogPictureCorner(
        canvas: Canvas?
    ){
        canvas?.drawWithMode(this){
            val dogBitmap = BitmapFactory.decodeResource(
                resources,
                R.drawable.dog
            )
            canvas.apply {
                drawRoundRect(
                    20f,
                    20f,
                    dogBitmap.width.toFloat(),
                    dogBitmap.height.toFloat(),
                    50f,
                    50f,
                    mPaint
                )
                mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
                drawBitmap(
                    dogBitmap,
                    20f,
                    20f,
                    mPaint
                )
            }
        }
    }

    // 创建一个圆形的目标图片
    private fun makeDst(
        width: Int = bitmapWidth,
        height: Int = bitmapHeight
    ) = Bitmap.createBitmap(
        width,
        height,
        Bitmap.Config.ARGB_8888
    ).run {
        val canvas = Canvas(this)
        // 抗锯齿
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        paint.color = Color.YELLOW
        canvas.drawOval(
            RectF(
                0f,
                0f,
                width.toFloat(),
                height.toFloat()
            ),
            paint
        )
        this
    }

    // 创建一个矩形的源图片
    private fun makeSrc(
        width: Int = bitmapWidth,
        height: Int = bitmapHeight
    ) = Bitmap.createBitmap(
        width,
        height,
        Bitmap.Config.ARGB_8888
    ).run {
        val canvas = Canvas(this)
        // 抗锯齿
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        paint.color = Color.BLUE
        canvas.drawRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            paint
        )
        this
    }
}