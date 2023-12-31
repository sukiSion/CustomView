package com.example.customview.volumeone.four

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TypeEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.customview.R

class ValuesHolderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_values_holder)
        val startButton: Button = findViewById(R.id.start_button_7)
        val imageView: ImageView = findViewById(R.id.animator_pic_2)
        val charText: CharTextView = findViewById(R.id.char_text_1)
        val imageView2: ImageView = findViewById(R.id.animator_pic_3)

        val rotationHolder = PropertyValuesHolder.ofFloat(
            "rotation",
            60f,
            -60f,
            40f,
            -40f,
            20f,
            -20f,
            10f,
            -10f,
            0f
        )

        val alphaHolder = PropertyValuesHolder.ofFloat(
            "alpha",
            0.1f,
            1f,
            0.1f,
            1f
        )

        val objectAnimatior = ObjectAnimator.ofPropertyValuesHolder(
            imageView,
            rotationHolder,
            alphaHolder
        ).apply {
            duration = 3000
        }

        val charEvaluator = object :TypeEvaluator<Char>{
            override fun evaluate(fraction: Float, startValue: Char?, endValue: Char?): Char {
                val startCode = startValue!!.code
                val endCode = endValue!!.code
                val curCode = (startCode + fraction * (endCode - startCode)).toInt()
                return curCode.toChar()
            }
        }

        val charHolder = PropertyValuesHolder.ofObject(
            "charText",
            charEvaluator,  'Z'
        )

        val charAnimator = ObjectAnimator.ofPropertyValuesHolder(
            charText,
            charHolder
        ).apply {
            duration = 5000
            interpolator = AccelerateInterpolator()
        }

        val keyframe = Keyframe.ofFloat(
            0f,
            0f
        )
        val keyframe1 = Keyframe.ofFloat(
            .5f,
            100f
        ).apply {
            interpolator = BounceInterpolator()
        }
        val keyframe2 = Keyframe.ofFloat(
            1f
        ).apply {
            value = 0f
            interpolator = BounceInterpolator()
        }

        val keyFrameHolders = PropertyValuesHolder.ofKeyframe(
            "rotation",
            keyframe,
            keyframe1,
            keyframe2
        )

        val keyFrameAnim = ObjectAnimator.ofPropertyValuesHolder(
            imageView,
            keyFrameHolders
        ).apply {
            duration = 3000
        }


        val charKeyFrame1 = Keyframe.ofObject(
            0f,
            'A'
        )
        val charKeyFrame2 = Keyframe.ofObject(
            0.1f,
            'L'
        )
        val charKeyFrame3 = Keyframe.ofObject(
            0.6f,
            'T'
        )

        val charKeyFrame4 = Keyframe.ofObject(
            1f,
            'Z'
        )

        val keyFrameValuesHolder = PropertyValuesHolder.ofKeyframe(
            "charText",
            charKeyFrame2,
            charKeyFrame3
        ).apply {
            setEvaluator(charEvaluator)
        }




        val charKeyFrameAnim = ObjectAnimator.ofPropertyValuesHolder(
            charText,
            keyFrameValuesHolder
        ).apply {
            duration = 3000
        }

        val shockKeyframe0 = Keyframe.ofFloat(0f , 0f)
        val shockKeyframe1 = Keyframe.ofFloat(0.1f , -20f)
        val shockKeyframe2 = Keyframe.ofFloat(0.2f , 20f)
        val shockKeyframe3 = Keyframe.ofFloat(0.3f , -20f)
        val shockKeyframe4 = Keyframe.ofFloat(0.4f , 20f)
        val shockKeyframe5 = Keyframe.ofFloat(0.5f , -20f)
        val shockKeyframe6 = Keyframe.ofFloat(0.6f , 20f)
        val shockKeyframe7 = Keyframe.ofFloat(0.7f , -20f)
        val shockKeyframe8 = Keyframe.ofFloat(0.8f , 20f)
        val shockKeyframe9 = Keyframe.ofFloat(0.9f , -20f)
        val shockKeyframe10 = Keyframe.ofFloat(1f , 0f)

        val scaleKeyframe0 = Keyframe.ofFloat(0f ,1f)
        val scaleKeyframe1 = Keyframe.ofFloat(0.1f ,1.1f)
        val scaleKeyframe2 = Keyframe.ofFloat(0.9f ,1.1f)
        val scaleKeyframe3 = Keyframe.ofFloat(1f ,1f)


        val shockPropertyValuesHolder = PropertyValuesHolder.ofKeyframe(
            "rotation",
            shockKeyframe0,
            shockKeyframe1,
            shockKeyframe2,
            shockKeyframe3,
            shockKeyframe4,
            shockKeyframe5,
            shockKeyframe6,
            shockKeyframe7,
            shockKeyframe8,
            shockKeyframe9,
            shockKeyframe10
        )

        val scaleXPropertyValuesHolder = PropertyValuesHolder.ofKeyframe(
            "scaleX",
            scaleKeyframe0,
            scaleKeyframe1,
            scaleKeyframe2,
            scaleKeyframe3
        )

        val scaleYPropertyValuesHolder = PropertyValuesHolder.ofKeyframe(
            "scaleY",
            scaleKeyframe0,
            scaleKeyframe1,
            scaleKeyframe2,
            scaleKeyframe3
        )


        val shockAnim = ObjectAnimator.ofPropertyValuesHolder(
            imageView,
            shockPropertyValuesHolder,
            scaleXPropertyValuesHolder,
            scaleYPropertyValuesHolder
        ).apply {
            duration = 3000
        }
        imageView2.visibility = View.GONE

        imageView.setOnClickListener {
            Toast.makeText(
                this,
                "点击了图片",
                Toast.LENGTH_SHORT
            ).show()
        }

        startButton.setOnClickListener {
            imageView.animate().translationX(200f)
                .translationY(600f)
        }
    }
}