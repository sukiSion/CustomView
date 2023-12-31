package com.example.customview.volumeone.three

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.customview.R

class AnimatorXmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_xml)
        val startButton: Button = findViewById(R.id.start_button_7)
        val animatorPic1: ImageView = findViewById(R.id.animator_pic_2)

        val valueAnimator: ValueAnimator = AnimatorInflater.loadAnimator(
            this,
            R.animator.valueanim
        ) as ValueAnimator

        valueAnimator.addUpdateListener {
            val offestX = it.animatedValue as Int
            animatorPic1.layout(
                offestX,
                animatorPic1.top,
                offestX + animatorPic1.width,
                animatorPic1.height + animatorPic1.top
            )
        }

        val objectAnimator = AnimatorInflater.loadAnimator(
            this,
            R.animator.objectanim
        ) as ObjectAnimator
        objectAnimator.target = animatorPic1

        startButton.setOnClickListener {
            objectAnimator.start()
        }
    }
}