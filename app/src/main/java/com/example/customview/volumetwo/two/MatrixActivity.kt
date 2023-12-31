package com.example.customview.volumetwo.two

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.example.customview.R
import com.example.customview.databinding.ActivityMatrixBinding

class MatrixActivity : AppCompatActivity() {

    private lateinit var activityMatrixBinding: ActivityMatrixBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMatrixBinding = ActivityMatrixBinding.inflate(layoutInflater)
        setContentView(activityMatrixBinding.root)
        activityMatrixBinding.btnTranslate.setOnClickListener {
            activityMatrixBinding.mimgTranslate.rotateX(45f)
        }
        activityMatrixBinding.sbProgress.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                activityMatrixBinding.mimgTranslate.rotate(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

    }
}