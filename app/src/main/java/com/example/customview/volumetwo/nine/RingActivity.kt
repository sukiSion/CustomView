package com.example.customview.volumetwo.nine

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import com.example.customview.databinding.ActivityRingBinding

class RingActivity : AppCompatActivity() {
    private lateinit var activityRingBinding: ActivityRingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRingBinding = ActivityRingBinding.inflate(layoutInflater)
        setContentView(activityRingBinding.root)
        activityRingBinding.rv.apply {
            setDatas(ArrayList<RingBean>().apply {
                add(RingBean(Color.RED , 30f))
                add(RingBean(Color.GREEN , 60f))
                add(RingBean(Color.GRAY , 50f))
                add(RingBean(Color.BLUE , 40f))
                add(RingBean(Color.BLACK , 120f))
                add(RingBean(Color.YELLOW , 60f))
            })
            setProgressAnimation(2000)
        }
    }
}