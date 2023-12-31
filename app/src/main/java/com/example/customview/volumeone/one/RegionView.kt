package com.example.customview.volumeone.one

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class RegionView: View {
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

        // 定义画笔
        val paint = Paint().apply {
            style = Paint.Style.FILL
            color = Color.RED
        }

//        val region = Region(
//            Rect(
//                50,
//                50,
//                200,
//                100
//            )
//        )
//        drawRegion(
//            canvas,
//            region,
//            paint
//        )

        // 定义矩形
        val rect = RectF(
            50f,
            50f,
            200f,
            500f
        )

        // 根据矩形画一个椭圆的路径
        val ovalPath = Path().apply {
            addOval(
                rect,
                Path.Direction.CCW
            )
        }

        // 创建一个新的区域
        // 通过setPath设置区域
        // 第一参数是：椭圆的路径
        // 第二参数是：根据路径与区域的交集最终确定区域
        val rgn = Region().apply {
            setPath(
                ovalPath,
                Region(
                    50,
                    50,
                    200,
                    200
                )
            )
        }

        drawRegion(
            canvas,
            rgn,
            paint
        )


    }

    private fun drawRegion(canvas: Canvas? , rgn: Region , paint: Paint){
        // 区域遍历器
        val regionIterator = RegionIterator(rgn)
        // 使用空构造构造一个矩形78
        val r = Rect()
        while (regionIterator.next(r)){
            canvas?.drawRect(r , paint)
        }
    }
}