package com.example.customview.volumetwo.three

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class CustomView: View {
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

    private var mWidth = 0
    private var mHeight = 0
    private val mBitmap: Bitmap

    init {
        mBitmap = BitmapFactory.decodeResource(context.resources , R.mipmap.dog)
        mWidth = mBitmap.width
        mHeight = mBitmap.height
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(
                mWidth,
                mHeight
            )
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(
                mWidth,
                heightSize
            )
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(
                widthSize,
                mHeight
            )
        }else{
            setMeasuredDimension(
                widthSize,
                heightSize
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(mBitmap , 0f , 0f , null)
    }
}