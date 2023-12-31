package com.example.customview.volumetwo.three

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import com.example.customview.R

class CustomEditText: AppCompatEditText {
    private lateinit var mDeleteDrawable: Drawable
    constructor(context: Context) : super(context){
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        initData(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initData(context, attrs)
    }

    private fun initData(context: Context , attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomEditText
        )
        // 拿到对应的资源引用
        val ic_deleteResID = typedArray.getResourceId(
            R.styleable.CustomEditText_ic_delete,
            R.drawable.delete
        )
        mDeleteDrawable = resources.getDrawable(ic_deleteResID , null)
        mDeleteDrawable.bounds = Rect(0 , 0 , 80 , 80)
        // 回收
        typedArray.recycle()
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        setDeleteIconVisible(
            hasFocus() && (text?.length ?: 0) > 0
        )
    }

    private fun setDeleteIconVisible(deleteVisible: Boolean){
        // EditText自带设置Drawable函数
        setCompoundDrawables(
            null,
            null,
            if(deleteVisible) mDeleteDrawable else null,
            null
        )
        invalidate()
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        setDeleteIconVisible(
            focused && length() > 0
        )
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_UP -> {
                if(this::mDeleteDrawable.isInitialized
                    && event.x <= width - paddingEnd
                    && event.x >= width - paddingEnd - mDeleteDrawable.bounds.width()){
                    setText("")
                }
            }
        }
        return super.onTouchEvent(event)
    }
}