package com.example.customview.volumetwo.six

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewConfiguration
import com.example.customview.R
import com.example.customview.databinding.ActivityViewDragHelperBinding

class ViewDragHelperActivity : AppCompatActivity() {

    private lateinit var activityViewDragHelperBinding: ActivityViewDragHelperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewDragHelperBinding = ActivityViewDragHelperBinding.inflate(layoutInflater)
        setContentView(activityViewDragHelperBinding.root)
        activityViewDragHelperBinding.tv2.setOnClickListener{

        }

    }
}