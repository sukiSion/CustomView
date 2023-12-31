package com.example.customview.volumetwo.nine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import com.example.customview.databinding.ActivityHuaweiClockBinding

class HuaweiClockActivity : AppCompatActivity() {
    private lateinit var activityHuaweiClockBinding: ActivityHuaweiClockBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHuaweiClockBinding = ActivityHuaweiClockBinding.inflate(layoutInflater)
        setContentView(activityHuaweiClockBinding.root)
        activityHuaweiClockBinding.hcv.performAnimator()
    }
}