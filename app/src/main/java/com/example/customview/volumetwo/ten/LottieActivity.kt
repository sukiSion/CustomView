package com.example.customview.volumetwo.ten

import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.LottieTask
import com.airbnb.lottie.TextDelegate
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.ScaleXY
import com.airbnb.lottie.value.SimpleLottieValueCallback
import com.example.customview.R
import com.example.customview.databinding.ActivityLottieBinding

class LottieActivity : AppCompatActivity() {
    private lateinit var activityLottieBinding: ActivityLottieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLottieBinding = ActivityLottieBinding.inflate(layoutInflater)
        setContentView(activityLottieBinding.root)
//        activityLottieBinding.lav.setAnimationFromUrl("http://192.168.31.14:8080/Lottie/ball_map.zip")
        activityLottieBinding.btSwapText.setOnClickListener {
            activityLottieBinding.lav.setTextDelegate(
                TextDelegate(activityLottieBinding.lav).also {
                    it.setText("大家好，我是启舰" , "启的舰")
                }
            )
        }
        activityLottieBinding.btScale3.setOnClickListener {
            activityLottieBinding.lav.addValueCallback(
                KeyPath("“未标题-1”轮廓"),
                LottieProperty.TRANSFORM_SCALE,
                SimpleLottieValueCallback<ScaleXY>{
                    lottieFrameInfo ->
                    return@SimpleLottieValueCallback ScaleXY(
                        lottieFrameInfo.startValue.scaleX * 3f,
                        lottieFrameInfo.startValue.scaleY * 3f
                    )
                }
            )
        }
        activityLottieBinding.btChangeBgColor.setOnClickListener {
            activityLottieBinding.lav.addValueCallback(
                KeyPath("“未标题-1”轮廓", "组 1"  , "填充 1"),
                LottieProperty.COLOR,
                SimpleLottieValueCallback {
                    return@SimpleLottieValueCallback Color.RED
                }
            )
        }

//        LottieCompositionFactory.fromAsset(
//            this,
//            "data.json"
//        ).addListener {
//            activityLottieBinding.lav.apply {
//                setComposition(it)
//                updateBitmap(
//                    "image_0",
//                    BitmapFactory.decodeResource(
//                        resources,
//                        R.drawable.flower,
//                    )
//                )
//            }
//        }
    }
}