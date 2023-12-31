package com.example.customview.volumeone.eleven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import com.example.customview.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {
    private lateinit var activityViewBinding: ActivityViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewBinding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(activityViewBinding.root)

    }
}