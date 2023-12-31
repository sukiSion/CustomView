package com.example.customview.volumetwo.three

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import com.example.customview.databinding.ActivityCustomViewBinding

class CustomViewActivity : AppCompatActivity() {
    private lateinit var activityCustomViewBinding: ActivityCustomViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCustomViewBinding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(activityCustomViewBinding.root)

    }
}