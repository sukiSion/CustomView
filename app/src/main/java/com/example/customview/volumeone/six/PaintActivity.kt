package com.example.customview.volumeone.six

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.databinding.ActivityPaintBinding

class PaintActivity: AppCompatActivity() {

    private lateinit var activityPaintBinding: ActivityPaintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPaintBinding = ActivityPaintBinding.inflate(layoutInflater)
        setContentView(activityPaintBinding.root)

    }
}