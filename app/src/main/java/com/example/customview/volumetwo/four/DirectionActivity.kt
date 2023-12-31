package com.example.customview.volumetwo.four

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.example.customview.R
import com.example.customview.databinding.ActivityDirectionBinding

class DirectionActivity : AppCompatActivity() {
    private lateinit var activityDirectionBinding: ActivityDirectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDirectionBinding = ActivityDirectionBinding.inflate(layoutInflater)
        setContentView(activityDirectionBinding.root)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("directionActivity" , "${event?.action}")
        return super.onTouchEvent(event)
    }

}