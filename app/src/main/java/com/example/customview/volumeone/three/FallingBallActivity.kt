package com.example.customview.volumeone.three

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.graphics.Point
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.R

class FallingBallActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_falling_ball)
        val button:Button = findViewById(R.id.start_button_4)
        val ballImageView:CustomImageView = findViewById(R.id.ball_image_view)

        val fallingBallAnimator = ObjectAnimator.ofObject(
            ballImageView,
            "fallingPos",
            object : TypeEvaluator<Point>{
                private var point = Point(0 , 0)
                override fun evaluate(
                    fraction: Float,
                    startValue: Point?,
                    endValue: Point?
                ): Point {
                    point.x = (startValue!!.x + fraction * (endValue!!.x - startValue.x)).toInt()

                    if(fraction * 2 <= 1){
                        point.y = (startValue.y + 2 * fraction * (endValue.y - startValue.y)).toInt()
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
        }

        val scaleObjectAnimator = ObjectAnimator.ofFloat(
            ballImageView,
            "scaleSize",
            6f
        )

        button.setOnClickListener {
            scaleObjectAnimator.start()
        }
    }
}