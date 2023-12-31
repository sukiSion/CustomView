package com.example.customview.volumeone.three

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.example.customview.R

class MenuActivity : AppCompatActivity() {

    private var isClose : Boolean = true

    private lateinit var itemIcon1:ImageView
    private lateinit var itemIcon2:ImageView
    private lateinit var itemIcon3:ImageView
    private lateinit var itemIcon4:ImageView
    private lateinit var itemIcon5:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val menuIcon: ImageView = findViewById(R.id.menu_icon)
        itemIcon1 = findViewById(R.id.item_icon_1)
        itemIcon2 = findViewById(R.id.item_icon_2)
        itemIcon3 = findViewById(R.id.item_icon_3)
        itemIcon4 = findViewById(R.id.item_icon_4)
        itemIcon5 = findViewById(R.id.item_icon_5)

        menuIcon.setOnClickListener {
            if(isClose){
                // 打开
                isClose = false
                openMenu()
            }else{
                // 关闭
                isClose = true
                closeMenu()
            }
        }

        itemIcon1.setOnClickListener {
            Log.e("item" , "1")
        }

        itemIcon2.setOnClickListener {
            Log.e("item" , "2")

        }

        itemIcon3.setOnClickListener {
            Log.e("item" , "3")

        }

        itemIcon4.setOnClickListener {
            Log.e("item" , "4")

        }

        itemIcon5.setOnClickListener {
            Log.e("item" , "5")

        }
    }


    private fun openMenu(){
        openItemAnimator(itemIcon1 , 0)
        openItemAnimator(itemIcon2 , 1)
        openItemAnimator(itemIcon3 , 2)
        openItemAnimator(itemIcon4 , 3)
        openItemAnimator(itemIcon5 , 4)

    }

    private fun closeMenu(){
        closeItemAnimator(itemIcon1 , 0)
        closeItemAnimator(itemIcon2 , 1)
        closeItemAnimator(itemIcon3 , 2)
        closeItemAnimator(itemIcon4 , 3)
        closeItemAnimator(itemIcon5 , 4)

    }

    private fun closeItemAnimator(
        view: ImageView,
        index: Int,
        total: Int = 5,
        radius: Int = 300
    ){
        if(!view.isVisible){
            view.visibility = View.VISIBLE
        }
        val radian = Math.toRadians((90).toDouble()) / (total - 1) * index
        val translationX = -(radius * Math.sin(radian)).toFloat()
        val translationY = -(radius * Math.cos(radian)).toFloat()

        val objectAnimator1 = ObjectAnimator.ofFloat(
            view,
            "translationX",
            translationX,
            0f
        )

        val objectAnimator2 = ObjectAnimator.ofFloat(
            view,
            "translationY",
            translationY,
            0f
        )

        val objectAnimator3 = ObjectAnimator.ofFloat(
            view,
            "scaleX",
            1f,
            0f
        )

        val objectAnimator4 = ObjectAnimator.ofFloat(
            view,
            "scaleY",
            1f,
            0f
        )

        val objectAnimator5 = ObjectAnimator.ofFloat(
            view,
            "alpha",
            1f,
            0f
        )

        AnimatorSet().apply {
            playTogether(
                objectAnimator1,
                objectAnimator2,
                objectAnimator3,
                objectAnimator4,
                objectAnimator5
            )
            duration = 500
        }.start()

    }

    private fun openItemAnimator(
        view:ImageView,
        index:Int,
        total:Int = 5,
        radius:Int = 300
    ){
        if(view.visibility != View.VISIBLE){
            view.visibility = View.VISIBLE
        }

        val radian = ( Math.PI / 2 / (total - 1) ) * index
//        val radian = Math.toRadians((90).toDouble()) / (total - 1) * index
        val translationX = -(radius * Math.sin(radian)).toFloat()
        val translationY = -(radius * Math.cos(radian)).toFloat()

        val objectAnimator1 = ObjectAnimator.ofFloat(
            view,
            "translationX",
            0f,
            translationX
        )
        val objectAnimator2 = ObjectAnimator.ofFloat(
            view,
            "translationY",
            0f,
            translationY
        )
        val objectAnimator3 = ObjectAnimator.ofFloat(
            view,
            "scaleX",
            0f,
            1f
        )
        val objectAnimator4 = ObjectAnimator.ofFloat(
            view,
            "scaleY",
            0f,
            1f
        )
        val objectAnimator5 = ObjectAnimator.ofFloat(
            view,
            "alpha",
            0f,
            1f
        )



        val animatorSet = AnimatorSet().apply {
            playTogether(
                objectAnimator1,
                objectAnimator2,
                objectAnimator3,
                objectAnimator4,
                objectAnimator5
            )
            duration = 500
        }.start()
    }
}