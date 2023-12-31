package com.example.customview.volumetwo.three

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.LinearLayout

class CustomRoundLayout: LinearLayout {
    private val mPath by lazy {
        Path()
    }

    private lateinit var mBgColor:String
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

    override fun dispatchDraw(canvas: Canvas?) {
        mPath.reset()
        mPath.addRoundRect(
            RectF(
                0f , 0f,
                width.toFloat(),
                height.toFloat()
            ),
            50f,
            50f,
            Path.Direction.CW
        )
        canvas?.save()
        canvas?.clipPath(mPath)
        if(this::mBgColor.isInitialized){
            canvas?.drawColor(Color.parseColor(mBgColor))
        }
        super.dispatchDraw(canvas)
        canvas?.restore()
    }

    override fun onDraw(canvas: Canvas?) {
        if(!this::mBgColor.isInitialized){
            val bgDrawable = background
            if(bgDrawable is ColorDrawable){
                val color = bgDrawable.color
                mBgColor = '#' + String.format("%08x" , color)
            }
        }
        setBackgroundColor(Color.parseColor("#00FFFFFF"))
        super.onDraw(canvas)
    }


}