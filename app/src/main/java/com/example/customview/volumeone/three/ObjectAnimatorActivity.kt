package com.example.customview.volumeone.three

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.customview.R

class ObjectAnimatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)
        val button:Button = findViewById(R.id.start_button_3)
        val alphaImage: ImageView = findViewById(R.id.alpha_image)

        val rotationAnimator = ObjectAnimator.ofFloat(
            alphaImage,
            // 传入属性名字
            "rotation",
            0f,
            360f
        ).apply {
            duration = 2000

        }

        val rotationAnimatorX = ObjectAnimator.ofFloat(
            alphaImage,
            "rotationX",
            0f,
            270f,
            0f
        ).apply {
            duration = 2000
        }

        val rotationAnimatorY = ObjectAnimator.ofFloat(
            alphaImage,
            "rotationY",
            0f,
            180f,
            0f
        ).apply {
            duration = 2000
        }

        val translationAnimatorX = ObjectAnimator.ofFloat(
            alphaImage,
            "translationX",
            0f,
            200f,
            -200f,
            0f
        ).apply {
            duration = 2000
        }

        val translationAnimatorY = ObjectAnimator.ofFloat(
            alphaImage,
            "translationY",
            0f,
            200f,
            -100f,
            0f
        ).apply {
            duration = 2000
        }

        val scaleX = ObjectAnimator.ofFloat(
            alphaImage,
            "scaleX",
            0f,
            3f,
            1f
        ).apply {
            duration = 2000
        }

        val scaleY = ObjectAnimator.ofFloat(
            alphaImage,
            "scaleY",
            0f,
            3f,
            1f
        ).apply {
            duration = 2000
        }



        button.setOnClickListener {
            scaleY.start()
        }
    }
}