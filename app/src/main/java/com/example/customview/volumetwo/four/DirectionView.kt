package com.example.customview.volumetwo.four

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customview.R

class DirectionView: LinearLayout , OnClickListener {

    private lateinit var mContext: Context
    private var lastX = 0f
    private var lastY = 0f

    constructor(context: Context?) : super(context){
        context?.let { init(it) }
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        context?.let { init(it) }
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        context?.let { init(it) }
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        context?.let { init(it) }
    }

    private fun init(context: Context){
        mContext = context
        LayoutInflater.from(context).inflate(R.layout.layout_direction_view , this)
        findViewById<ImageView>(R.id.direction_up).setOnClickListener(this)
        findViewById<ImageView>(R.id.direction_down).setOnClickListener(this)
        findViewById<ImageView>(R.id.direction_left).setOnClickListener(this)
        findViewById<ImageView>(R.id.direction_right).setOnClickListener(this)
        findViewById<ImageView>(R.id.direction_ok).setOnClickListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_MOVE){
            val offsetX = event.x - lastX
            val offsetY = event.y - lastY
            val params = layoutParams as ConstraintLayout.LayoutParams
            params.leftMargin = (params.leftMargin + offsetX).toInt()
            params.topMargin = (params.topMargin + offsetY).toInt()
            this.layoutParams = params
            // 消费
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN -> {
                lastX = ev.x
                lastY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                // 拦截
                return true
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.direction_up -> {
                toast("up clicked")
            }
            R.id.direction_down -> {
                toast("down clicked")
            }
            R.id.direction_left -> {
                toast("left clicked")
            }
            R.id.direction_right -> {
                toast("right clicked")
            }
            R.id.direction_ok -> {
                toast("ok clicked")
            }
            else -> {

            }
        }
    }

    private fun toast(content: String){
        Toast.makeText(mContext , content , Toast.LENGTH_SHORT).show()
    }
}