package com.example.customview.volumetwo.two

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import com.example.customview.databinding.ActivitySetRectToRectBinding

class SetRectToRectActivity : AppCompatActivity() {

    private lateinit var activitySetRectToRectBinding: ActivitySetRectToRectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySetRectToRectBinding = ActivitySetRectToRectBinding.inflate(layoutInflater)
        setContentView(activitySetRectToRectBinding.root)
    }
}