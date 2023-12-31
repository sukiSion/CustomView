package com.example.customview.volumeone.three

import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.customview.R
import com.google.android.material.imageview.ShapeableImageView

class ParabolicAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parabolic_animation)
        val ball:ShapeableImageView = findViewById(R.id.ball)
        val startButton: Button = findViewById(R.id.start_button_2)

        val fallingBallAnime = ValueAnimator.ofObject(
           object : TypeEvaluator<Point>{
               private val point: Point = Point()
               override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {
                   // x轴方向上的速度是不变的
                   point.x = (startValue!!.x + fraction * (endValue!!.x - startValue.x)).toInt()

                   if(fraction *  2 < 1){
                       point .y = (startValue.y + fraction * 2 * (endValue.y - startValue.y)).toInt()
                   }else{
                       point.y = endValue.y
                   }

                   return point
               }
           },
            Point(0 , 0),
            Point(500 , 500)
        ).apply {
            duration = 2000
            addUpdateListener {
                val curValue = it.animatedValue as Point
                ball.layout(
                    curValue.x,
                    curValue.y,
                    curValue.x + ball.width,
                    curValue.y + ball.height
                )
            }
        }


        startButton.setOnClickListener {
            fallingBallAnime.start()
        }
    }
}