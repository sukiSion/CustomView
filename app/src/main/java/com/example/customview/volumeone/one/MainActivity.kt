package com.example.customview.volumeone.one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.CycleInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.TextView
import com.example.customview.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val textView: TextView = findViewById(R.id.textView)
        val scaleAnimation = ScaleAnimation(
            0.0f,
            1.4f,
            0.0f,
            1.4f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 700
            fillAfter = true

        }

        val alphaAnimation = AlphaAnimation(
            1.0f,
            0.1f
        ).apply {
            duration = 3000
            fillBefore = true
            // 设置动画的插值器
            interpolator = LinearInterpolator()
        }

        val rotateAnimation = RotateAnimation(
            0f,
            -650f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            fillAfter = true
            duration = 3000
        }

        val translateAnimation = TranslateAnimation(
            Animation.ABSOLUTE,
            0f,
            Animation.ABSOLUTE,
            -80f,
            Animation.ABSOLUTE,
            0f,
            Animation.ABSOLUTE,
            -80f,
        ).apply {
            duration = 2000
            fillBefore = true
        }

        val alpha_anim = AlphaAnimation(
            0.1f,
            1.0f
        )

        val scale_anim = ScaleAnimation(
            0.0f,
            1.4f,
            0.0f,
            1.4f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        val rotate_anim = RotateAnimation(
            0f,
            720f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        val animationSet = AnimationSet(true).apply {
            addAnimation(scale_anim)
            addAnimation(alpha_anim)
            addAnimation(rotate_anim)
            duration = 3000
            fillAfter = true
        }


        val rotateAnim = RotateAnimation(
            0f,
            -650f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 3000
            fillAfter = true
        }

        val scaleAnim = ScaleAnimation(
            0.0f,
            1.4f,
            0.0f,
            1.4f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 700
            fillAfter = true
            setAnimationListener(object : AnimationListener{
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    textView.startAnimation(rotateAnimation)
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }
            })
        }
        val animation = AnimationUtils.loadAnimation(
            this,
            R.anim.rotateanim1
        ).apply {
            interpolator = CycleInterpolator(1f)
            fillAfter = true
        }



        button.setOnClickListener {

            textView.startAnimation(animation)
        }
    }
}