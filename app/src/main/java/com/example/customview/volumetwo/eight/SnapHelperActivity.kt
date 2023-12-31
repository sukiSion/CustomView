package com.example.customview.volumetwo.eight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.customview.R
import com.example.customview.databinding.ActivitySnapHelperBinding

class SnapHelperActivity : AppCompatActivity() {

    private lateinit var activitySnapHelperBinding: ActivitySnapHelperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySnapHelperBinding = ActivitySnapHelperBinding.inflate(layoutInflater)
        setContentView(activitySnapHelperBinding.root)
        activitySnapHelperBinding.rv.apply {
            layoutManager = LinearLayoutManager(this@SnapHelperActivity)
            adapter = SnapHelperAdapter(200)
//            LinearSnapHelper().attachToRecyclerView(this)
            PagerSnapHelper().attachToRecyclerView(this)
        }

    }
}