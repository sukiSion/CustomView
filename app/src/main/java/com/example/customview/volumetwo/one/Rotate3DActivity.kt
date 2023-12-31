package com.example.customview.volumetwo.one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import com.example.customview.R
import com.example.customview.databinding.ActivityRotate3DactivityBinding

class Rotate3DActivity : AppCompatActivity() {

    private lateinit var activityRotate3DactivityBinding: ActivityRotate3DactivityBinding
    companion object{
        private const val animationDuration = 600L
    }
    private var isOpening = false
    private var isClosing = false
    private val openAnimation by lazy {
         Rotate3DAnimation(0f , 90f , true).apply {
             duration = animationDuration
             fillAfter = true
             interpolator = AccelerateInterpolator()
             setAnimationListener(object : AnimationListener{
                 override fun onAnimationStart(animation: Animation?) {
                     isOpening = true
                 }

                 override fun onAnimationEnd(animation: Animation?) {
//                     activityRotate3DactivityBinding.ivLogo.setImageDrawable(
//                         ContextCompat.getDrawable(
//                             this@Rotate3DActivity,
//                             R.drawable.photo2
//                         )
//                     )
                     activityRotate3DactivityBinding.ivLogo.visibility = View.GONE
                     activityRotate3DactivityBinding.ivLogo2.visibility = View.VISIBLE
                     Rotate3DAnimation(90f , 180f , false).apply {
                         duration = animationDuration
                         fillAfter = true
                         interpolator = DecelerateInterpolator()
                         setAnimationListener(object : AnimationListener{
                             override fun onAnimationStart(animation: Animation?) {

                             }

                             override fun onAnimationEnd(animation: Animation?) {
                                 isOpening  = false
                             }

                             override fun onAnimationRepeat(animation: Animation?) {
                             }
                         })
                     }.also {
                         activityRotate3DactivityBinding.content.startAnimation(it)
                     }

                 }

                 override fun onAnimationRepeat(animation: Animation?) {
                 }
             })
        }
    }

    private val closeAnimation by lazy {
        Rotate3DAnimation(180f , 90f , true).apply {
            duration = animationDuration
            fillAfter = true
            interpolator = AccelerateInterpolator()
            setAnimationListener(object : AnimationListener{
                override fun onAnimationStart(animation: Animation?) {
                    isClosing = true
                }

                override fun onAnimationEnd(animation: Animation?) {
//                    activityRotate3DactivityBinding.ivLogo.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            this@Rotate3DActivity,
//                            R.drawable.photo1
//                        )
//                    )
                    activityRotate3DactivityBinding.ivLogo.visibility = View.VISIBLE
                    activityRotate3DactivityBinding.ivLogo2.visibility = View.GONE
                    Rotate3DAnimation(90f , 0f , false).apply {
                        duration = animationDuration
                        fillAfter = true
                        interpolator = DecelerateInterpolator()
                        setAnimationListener(object : AnimationListener{
                            override fun onAnimationStart(animation: Animation?) {

                            }

                            override fun onAnimationEnd(animation: Animation?) {
                                isClosing = false
                            }

                            override fun onAnimationRepeat(animation: Animation?) {
                            }
                        })
                    }.also {
                        activityRotate3DactivityBinding.content.startAnimation(it)
                    }

                }

                override fun onAnimationRepeat(animation: Animation?) {
                }
            })
        }
    }

    private var isOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRotate3DactivityBinding = ActivityRotate3DactivityBinding.inflate(layoutInflater)
        setContentView(activityRotate3DactivityBinding.root)
        activityRotate3DactivityBinding.btnRotate.setOnClickListener {
            if(isOpening){
                return@setOnClickListener
            }
            if(isClosing){
                return@setOnClickListener
            }
            if(isOpen){
                activityRotate3DactivityBinding.content.startAnimation(closeAnimation)
            }else{
                activityRotate3DactivityBinding.content.startAnimation(openAnimation)
            }
            isOpen = !isOpen
        }
    }


}