package com.example.customview.volumeone.two

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.customview.R

class FrameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)
        val imageView: ImageView = findViewById(R.id.imageView2)
        val anim: AnimationDrawable = AnimationDrawable().run {
            for(i in 1..14 ){
                val drawableId = resources.getIdentifier(
                    "list_icon_gif_playing" + i,
                    "drawable",
                    packageName
                )
                val drawable = resources.getDrawable(drawableId)
                // 设置资源和动画持续时间
                addFrame(drawable ,60)
            }
            isOneShot = false
            this
        }
        imageView.background = anim

        val startButton: Button = findViewById(R.id.start_button)
        val stopButton: Button = findViewById(R.id.stop_button)
        startButton.setOnClickListener {
            anim.start()
        }
        stopButton.setOnClickListener {
            anim.stop()
        }
    }
}