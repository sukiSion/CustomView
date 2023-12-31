package com.example.customview.volumetwo.six

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import com.example.customview.databinding.ActivityTestScrollBinding

class TestScrollActivity : AppCompatActivity() {
    private lateinit var activityTestScrollBinding: ActivityTestScrollBinding
    companion object{
        const val DISTANCE = 300
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTestScrollBinding = ActivityTestScrollBinding.inflate(layoutInflater)
        setContentView(activityTestScrollBinding.root)
        activityTestScrollBinding.apply {
            btScrollBy.setOnClickListener {
                llcScroll.scrollBy(50 , 0)
            }
            btScrollTo.setOnClickListener {
                llcScroll.startScroller(0 , DISTANCE)
            }
            btReset.setOnClickListener {
                llcScroll.startScroller(DISTANCE , -DISTANCE)
            }
        }
    }


}