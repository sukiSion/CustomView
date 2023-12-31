package com.example.customview.volumeone.five

import android.graphics.drawable.Animatable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.customview.R

class SVGActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_svgactivity)
        val lineImage:ImageView = findViewById(R.id.line_image)
        val editText: EditText = findViewById(R.id.edit_text)

        val animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(
            this,
            R.drawable.animated_vector_search
        )

        lineImage.setImageDrawable(animatedVectorDrawableCompat)

        editText.onFocusChangeListener = object : View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if(hasFocus){
                    (lineImage.drawable as Animatable).start()
                }
            }
        }

//        val animatedVectorDrawableCompat = AnimatedVectorDrawableCompat
//            .create(
//                this,
//                R.drawable.line_animated_vector
//            )
//        lineImage.setImageDrawable(animatedVectorDrawableCompat)
//        val imageButton: Button = findViewById(R.id.image_button)
//        imageButton.setOnClickListener {
//            (lineImage.drawable as Animatable).start()
//        }
    }
}