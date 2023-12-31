package com.example.customview.volumeone.eleven

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.customview.R

@SuppressLint("SetTextI18n")
class MyTextView: AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        val typedArray = context.obtainStyledAttributes(attrs , R.styleable.MyTextView)
        val age = typedArray.getInt(R.styleable.MyTextView_age , 0)
        val headerHeight = typedArray.getDimension(R.styleable.MyTextView_headerHeight , 0f)
        val headerVisableHeight = typedArray.getDimension(R.styleable.MyTextView_headerVisableHeight , 0f)
        val header = typedArray.getResourceId(R.styleable.MyTextView_header , 0)
        typedArray.recycle()
        setText(
            "header: ${header} headerHeight: ${headerHeight} headerVisableHeight: ${headerVisableHeight} age ${age}"
        )
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val mode = MeasureSpec.getMode(widthMeasureSpec)
    }


}