package com.example.customview.volumeone.ten

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import com.example.customview.databinding.ActivitySurfaceViewBinding

class SurfaceViewActivity : AppCompatActivity() {
    private lateinit var activitySurfaceViewBinding: ActivitySurfaceViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySurfaceViewBinding = ActivitySurfaceViewBinding.inflate(layoutInflater)
        setContentView(activitySurfaceViewBinding.root)

    }
}