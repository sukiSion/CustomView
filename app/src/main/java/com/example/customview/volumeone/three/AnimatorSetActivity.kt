package com.example.customview.volumeone.three

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.core.animation.addListener
import com.example.customview.R

class AnimatorSetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_set)
        val startButton: Button = findViewById(R.id.start_button_5)
        val cancelButton: Button = findViewById(R.id.cancel_button_2)
        val animatorSet1: ImageView = findViewById(R.id.animator_set_1)
        val animatorSet2: ImageView = findViewById(R.id.animator_set_2)

        val animator1 = ObjectAnimator.ofInt(
            animatorSet1,
            "backgroundColor",
            (0xffff00ff).toInt(),
            (0xffffff00).toInt(),
            (0xffff00ff).toInt()
        )

        val animator2 = ObjectAnimator.ofFloat(
            animatorSet1,
            "translationY",
            0f,
            300f,
            0f
        ).apply {
        }

        val animator3 = ObjectAnimator.ofFloat(
            animatorSet2,
            "translationY",
            0f,
            400f,
            0f
        ).apply {
            startDelay = 2000
        }


        val animatorSet = AnimatorSet().apply {
            // 该duration是整个组合动画持续的时间
//            duration = 2000
//            playTogether(
//                animator1,
//                animator2,
//                animator3
//            )
//            startDelay = 2000

            addListener (
                onEnd = {
                        Log.e("animator" , "动画结束")
                },
                onStart = {
                    Log.e("animator" , "动画开始")

                },
                onCancel = {
                    Log.e("animator" , "动画取消")

                }
            ){
                // 这里是onRepeat
                Log.e("animator" , "动画重复")
            }
        }
        animatorSet.play(animator3).apply {
            with(animator2)
            // 动画2和3在动画1之后同时执行
//            after(animator1)
        }

        animatorSet.apply {
            startDelay = 2000
            duration = 2000
        }

        // 虽然单个动画都是作用在1，但是只要修改了目标控件就会作用到其他控件上
//        animatorSet.setTarget(animatorSet2)

        cancelButton.setOnClickListener {
            animatorSet.cancel()
        }

        startButton.setOnClickListener {
            animatorSet.start()
        }
    }
}