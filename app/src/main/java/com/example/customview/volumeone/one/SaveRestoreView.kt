package com.example.customview.volumeone.one

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class SaveRestoreView: View {
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

        canvas?.drawColor(
            Color.RED
        )

        // 将整屏的画布保存到栈顶
        canvas?.save()

        canvas?.clipRect(Rect(
            100,
            100,
            800,
            800
        ))

        canvas?.drawColor(Color.GREEN)

        // 从栈顶取出画布
        canvas?.restore()

        canvas?.drawColor(
            Color.BLUE
        )
    }
}
