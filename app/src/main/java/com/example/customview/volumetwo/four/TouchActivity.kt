package com.example.customview.volumetwo.four

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.example.customview.R
import com.example.customview.databinding.ActivityTouchBinding

class TouchActivity : AppCompatActivity() {
    private lateinit var activityTouchBinding: ActivityTouchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTouchBinding = ActivityTouchBinding.inflate(layoutInflater)
        setContentView(activityTouchBinding.root)

    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e(this.javaClass.simpleName , "dispatchTouchEvent(ev: MotionEvent?)")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e(this.javaClass.simpleName , "onTouchEvent(event: MotionEvent?)")
        return super.onTouchEvent(event)
    }

}