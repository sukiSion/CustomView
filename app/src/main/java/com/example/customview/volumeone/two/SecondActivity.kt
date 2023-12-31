package com.example.customview.volumeone.two

import android.annotation.SuppressLint
import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import com.example.customview.R
import com.google.android.material.imageview.ShapeableImageView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val imageView:ImageView = findViewById(R.id.imageView)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        val scaleAnimation = ScaleAnimation(
            1.0f,
            1.2f,
            1.0f,
            1.2f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            fillAfter = true
            duration = 6000
            interpolator = BounceInterpolator()
        }

        val rotateAnimation = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 2000
            interpolator = LinearInterpolator()
            repeatCount = Animation.INFINITE
        }

        val scanImg1: ImageView = findViewById(R.id.scan_img_1)
        val scanImg2: ImageView = findViewById(R.id.scan_img_2)
        val scanImg3: ImageView = findViewById(R.id.scan_img_3)
        val scanImg4: ImageView = findViewById(R.id.scan_img_4)

//         imageView.startAnimation(rotateAnimation)
        val animation = AnimationUtils.loadAnimation(
            this,
            R.anim.scale_alpha_anim
        )

        val animation1 = AnimationUtils.loadAnimation(
            this,
            R.anim.scale_alpha_anim
        ).apply {
            // 开始的偏移量
            startOffset = 600
        }

        val animation2 = AnimationUtils.loadAnimation(
            this,
            R.anim.scale_alpha_anim
        ).apply {
            // 开始的偏移量
            startOffset = 1200
        }

        val animation3 = AnimationUtils.loadAnimation(
            this,
            R.anim.scale_alpha_anim
        ).apply {
            // 开始的偏移量
            startOffset = 1800
        }

        imageView.setOnClickListener {
            scanImg1.startAnimation(animation)
            scanImg2.startAnimation(animation1)
            scanImg3.startAnimation(animation2)
            scanImg4.startAnimation(animation3)
        }
    }


}