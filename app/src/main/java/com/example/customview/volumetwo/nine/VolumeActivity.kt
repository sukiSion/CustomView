package com.example.customview.volumetwo.nine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import com.example.customview.databinding.ActivityVolumeBinding

class VolumeActivity : AppCompatActivity() {
    private lateinit var activityVolumeBinding: ActivityVolumeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityVolumeBinding = ActivityVolumeBinding.inflate(layoutInflater)
        setContentView(activityVolumeBinding.root)
        activityVolumeBinding.apply {
            increaseButton.setOnClickListener {
                vv.volunmeUp()
            }
            reduceButton.setOnClickListener {
                vv.volumeDown()
            }
        }
    }
}