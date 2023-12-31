package com.example.customview.volumeone.nine

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.customview.R
import com.example.customview.databinding.ActivityCanvasBinding

class CanvasActivity : AppCompatActivity() {
    private lateinit var activityCanvasBinding: ActivityCanvasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCanvasBinding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(activityCanvasBinding.root)

    }
}