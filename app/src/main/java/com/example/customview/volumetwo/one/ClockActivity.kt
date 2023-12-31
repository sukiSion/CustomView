package com.example.customview.volumetwo.one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import com.example.customview.databinding.ActivityClockBinding

class ClockActivity : AppCompatActivity() {

    private lateinit var activityClockBinding: ActivityClockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityClockBinding = ActivityClockBinding.inflate(layoutInflater)
        setContentView(activityClockBinding.root)
    }
}