package com.example.customview.volumeone.three

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatImageView
import com.example.customview.R

class LoadingImageView:AppCompatImageView {

    private var mTop: Int? = null
    private lateinit var valueAnim:ValueAnimator

    private var mCurImageIndex = 0
    private val mImageCount = 3

    constructor(context: Context) : super(context){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init()
    }



    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        // 布局控件时top为它的初始位置
        mTop = top
    }

    private fun init(){
       valueAnim = ValueAnimator.ofInt(0 , 100, 0).apply {
           repeatMode = ValueAnimator.REVERSE
           repeatCount = Animation.INFINITE
           duration = 2000
           interpolator = LinearInterpolator()
           // 动画值变化监听
           addUpdateListener {
               val x = it.animatedValue as Int
               mTop?.apply {
                   this@LoadingImageView.top = this - x
               }
           }
           // 动画状态监听
           addListener(object : AnimatorListener{
               override fun onAnimationStart(animation: Animator) {
                   this@LoadingImageView.setImageDrawable(
                       resources.getDrawable(R.drawable.pic_1)
                   )
               }

               override fun onAnimationEnd(animation: Animator) {

               }

               override fun onAnimationCancel(animation: Animator) {
               }

               override fun onAnimationRepeat(animation: Animator) {
                   mCurImageIndex += 1
                   when(mCurImageIndex % mImageCount){
                       0 -> {
                           this@LoadingImageView.setImageDrawable(
                               resources.getDrawable(R.drawable.pic_1)
                           )
                       }
                       1 -> {
                           this@LoadingImageView.setImageDrawable(
                               resources.getDrawable(R.drawable.pic_2)
                           )
                       }
                       2 -> {
                           this@LoadingImageView.setImageDrawable(
                               resources.getDrawable(R.drawable.pic_3)
                           )
                       }
                   }
               }
           })
       }
        valueAnim.start()
    }
}