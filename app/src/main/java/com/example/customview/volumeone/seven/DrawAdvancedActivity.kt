package com.example.customview.volumeone.seven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import com.example.customview.databinding.ActivityDrawAdvancedBinding

class DrawAdvancedActivity : AppCompatActivity() {
    private lateinit var activityDrawAdvancedBinding: ActivityDrawAdvancedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDrawAdvancedBinding = ActivityDrawAdvancedBinding.inflate(layoutInflater)
        setContentView(activityDrawAdvancedBinding.root)
//        activityDrawAdvancedBinding.apply {
//            showShadowLayerButton.setOnClickListener {
//                shadowLayerView.setShadowLayer()
//            }
//            clearShadowLayerButton.setOnClickListener {
//                shadowLayerView.clearShadowLayer()
//            }
//        }
    }

}