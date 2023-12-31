package com.example.customview.volumetwo.five

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.example.customview.R
import com.example.customview.databinding.ActivityMultiTouchBinding

class MultiTouchActivity : AppCompatActivity() {

    companion object{
        val TAG = MultiTouchActivity::class.java.simpleName
    }

    private lateinit var activityMultiTouchBinding: ActivityMultiTouchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMultiTouchBinding = ActivityMultiTouchBinding.inflate(layoutInflater)
        setContentView(activityMultiTouchBinding.root)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val index = event?.actionIndex
        when(event?.actionMasked){
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG , "第一根手指按下 index: ${index}")
            }
            MotionEvent.ACTION_POINTER_DOWN ->{
                Log.e(TAG , "又一根手指按下 index: ${index}")
            }
            MotionEvent.ACTION_POINTER_UP -> {
                Log.e(TAG , "不是最后一根手指抬起 index: ${index}")
            }
            MotionEvent.ACTION_UP  -> {
                Log.e(TAG , "最后一根手指抬起 index: ${index}")
            }
            MotionEvent.ACTION_MOVE -> {
//                Log.e(TAG ,"当前移动的手指 index: ${index}")
                historyMotionEvent(event)
            }
        }
        return super.onTouchEvent(event)
    }


    // 只能收集Move事件
    private fun historyMotionEvent(event: MotionEvent?){
        event?.also {
            val histortySize = it.historySize
            val pointerCount = it.pointerCount
            for (i in 0 until histortySize) {
                Log.e(TAG, "At time ${it.getHistoricalEventTime(i)}")
                for (j in 0 until pointerCount) {
                    Log.e(
                        TAG,
                        "pointer ${it.getPointerId(j)}: (${it.getHistoricalX(j, i)} , ${it.getHistoricalY(j, i)})"
                    )
                }
            }
            Log.e(TAG, "At time ${it.eventTime}")
            for (i in 0 until pointerCount) {
                Log.e(TAG, "pointer ${it.getPointerId(i)}: (${it.getX(i)} , ${it.getY(i)})")
            }

        }
    }

    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {

          return super.onGenericMotionEvent(event)
    }
}