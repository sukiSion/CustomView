package com.example.customview.volumeone.three

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ArgbEvaluator
import android.animation.IntEvaluator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.customview.R

class ValueAnimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_anime)
        val picture1: ImageView = findViewById(R.id.picture_1)
        val startButton: Button = findViewById(R.id.start_button_1)
        val cancaelButton: Button = findViewById(R.id.cancel_button_1)
        val argbEvationPic: ImageView = findViewById(R.id.argb_evalation_pic)
        val charText: TextView = findViewById(R.id.char_text)
        val translateAnim = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            1f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            1f
        ).apply {
            duration = 1000
            fillAfter = true
        }
        val valueAnime = ValueAnimator.ofInt(0 , 10).apply {
            duration = 1000
            addUpdateListener {
                val curValue = it.animatedValue as Int
                /**
                 *
                 * 为视图及其所有后代分配大小和位置
                 * 这是布局机制的第二阶段。 （首先是测量）。 在此阶段，每个父级对其所有子级调用布局来定位它们。 这通常使用存储在测量 pass() 中的子测量来完成。
                 * 派生类不应覆盖此方法。 带有子项的派生类应该覆盖 onLayout。 在那种方法中，他们应该在他们的每个孩子上调用布局。
                 * 参数：
                 * l – 左侧位置，相对于父级 t – 顶部位置，相对于父级 r – 右侧位置，相对于父级 b – 底部位置，相对于父级
                 *
                 */
                picture1.layout(
                    curValue  + picture1.left,
                    curValue + picture1.top,
                    curValue + picture1.width + picture1.left,
                    curValue + picture1.height + picture1.top
                )
            }

        }

        val floatValueAnime = ValueAnimator.ofFloat(
            0f, 400f,50f, 300f
        ).apply {
            duration = 3000
            addUpdateListener {
                val curValue = (it.animatedValue as Float).toInt()
                picture1.layout(
                    curValue,
                    curValue,
                    curValue + picture1.width,
                    curValue + picture1.height
                )
            }
        }

        val valueAnim = ValueAnimator.ofInt(0 , 400).apply {
            // 循环次数
            repeatCount = Animation.INFINITE
            // 循环模式
            repeatMode = ValueAnimator.REVERSE
            // 持续时间
            duration = 1000
            // 动画值更新监听
            addUpdateListener {
                val curValue = it.animatedValue as Int
                picture1.layout(
                    picture1.left,
                    curValue,
                    picture1.right,
                    picture1.height + curValue
                )
            }
            addListener(object : AnimatorListener{
                override fun onAnimationStart(animation: Animator) {
                    Log.e("animation" , "开始")
                }

                override fun onAnimationEnd(animation: Animator) {
                    Log.e("animation" , "结束")
                }

                override fun onAnimationCancel(animation: Animator) {
                    Log.e("animation" , "取消")

                }

                override fun onAnimationRepeat(animation: Animator) {
                    Log.e("animation" , "重复")
                }
            })
        }
        val colorAnime = ValueAnimator.ofInt(0xffffff00.toInt() , 0xff0000ff.toInt()).apply {
            duration = 3000
            setEvaluator(ArgbEvaluator())
            addUpdateListener {
                val curValue = it.animatedValue as Int
                argbEvationPic.setBackgroundColor(curValue)
            }
        }
        val newValueAnime = valueAnim.clone().apply {
//            interpolator = MyInterpolator()
            repeatCount = 0
            setEvaluator(ReverseEvaluator())
        }
        val valueAnim1 = ValueAnimator.ofObject(
            object : TypeEvaluator<Char>{
                override fun evaluate(fraction: Float, startValue: Char?, endValue: Char?): Char {
                    val startCode = startValue!!.code
                    val endCode = endValue!!.code
                    val curCode = (startCode + fraction * (endCode - startCode)).toInt()
                    val curChar = curCode.toChar()
                    return  curChar
                }
            },
            'A' , 'Z'
        ).apply {
            addUpdateListener {
                val char = it.animatedValue as Char
                charText.text = "${char}"
            }
            duration = 10000
            interpolator = AccelerateInterpolator()

        }
        startButton.setOnClickListener {

            valueAnim1.start()
        }
        cancaelButton.setOnClickListener {
//            newValueAnime.apply {
//                cancel()
//                removeAllUpdateListeners()
//                removeAllListeners()
//            }
            valueAnim1.cancel()
        }
        picture1.setOnClickListener {
            Toast.makeText(
                this,
                "点我",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}