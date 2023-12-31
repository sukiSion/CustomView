package com.example.customview.volumeone.four

import android.animation.Keyframe
import android.animation.LayoutTransition
import android.animation.LayoutTransition.TransitionListener
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.example.customview.R

class LayoutAnimActivity : AppCompatActivity() {

    private lateinit var addButton: Button
    private lateinit var deleteButton: Button
    private lateinit var containerLayout: LinearLayout
    private var count = 0

    @SuppressLint("ObjectAnimatorBinding")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_anim)


        addButton = findViewById(R.id.add_button)
        deleteButton = findViewById(R.id.delete_button)
        containerLayout = findViewById(R.id.container_layout)


        // 创建布局动画（用于控制子控件的动画）
//        val transition = LayoutTransition()
//
//        val animIn = ObjectAnimator.ofFloat(
//            null,
//            "rotationY",
//            0f,
//            360f,
//           0f
//        )
//        transition.setAnimator(
//            LayoutTransition.APPEARING,
//            animIn
//        )
//
//        val animOut = ObjectAnimator.ofFloat(
//            null,
//            "rotation",
//            0f,
//            90f,
//            0f
//        )
//        transition.setAnimator(
//            LayoutTransition.DISAPPEARING,
//            animOut
//        )

//        containerLayout.layoutTransition = transition

//        val transition1 = LayoutTransition()
//        val pvhLeft = PropertyValuesHolder.ofInt(
//            "left", 0 ,0
//        )
//        val pvhTop = PropertyValuesHolder.ofInt(
//            "top",0 , 0
//        )
//        val pvhScaleX = PropertyValuesHolder.ofFloat(
//            "scaleX", 1f , 0f , 1f
//        )
//        val pvhScaleY = PropertyValuesHolder.ofFloat(
//            "scaleY", 1f , 0f , 1f
//        )

//        val changeAppearAnimator = ObjectAnimator.ofPropertyValuesHolder(
//            containerLayout,
//            pvhLeft,
//            pvhTop,
//            pvhScaleX,
//            pvhScaleY
//        )

//        transition1.setAnimator(
//            LayoutTransition.CHANGING,
//            changeAppearAnimator
//        )
//
//        transition1.enableTransitionType(LayoutTransition.CHANGING)
//
//        containerLayout.layoutTransition = transition1

//        val transition = LayoutTransition()
//        val pvhLeft = PropertyValuesHolder.ofInt("left", 0, 0)
//        val pvhTop = PropertyValuesHolder.ofInt("top", 0, 0)
//        val pvhScaleX = PropertyValuesHolder.ofFloat("scaleY", 0.1f , 0f , 0.1f);
//        val changeAppearAnimator
//        = ObjectAnimator.ofPropertyValuesHolder(containerLayout, pvhLeft, pvhTop, pvhScaleX)
//        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeAppearAnimator)

        val  transition1 = LayoutTransition()

        val outLeft = PropertyValuesHolder.ofInt("left" , 0 , 0)
        val outTop = PropertyValuesHolder.ofInt("top" , 0 , 0)

        val frame0 = Keyframe.ofFloat(0f , 0f)
        val frame1 = Keyframe.ofFloat(0.1f , -20f)
        val frame2 = Keyframe.ofFloat(0.2f , 20f)
        val frame3 = Keyframe.ofFloat(0.3f , -20f)
        val frame4 = Keyframe.ofFloat(0.4f , 20f)
        val frame5 = Keyframe.ofFloat(0.5f , -20f)
        val frame6 = Keyframe.ofFloat(0.6f , 20f)
        val frame7 = Keyframe.ofFloat(0.7f , -20f)
        val frame8 = Keyframe.ofFloat(0.8f , 20f)
        val frame9 = Keyframe.ofFloat(0.9f , -20f)
        val frame10 = Keyframe.ofFloat(1f , 0f)

        val shockPropertyValuesHolder = PropertyValuesHolder.ofKeyframe(
            "rotation" ,
            frame0,
            frame1,
            frame2,
            frame3,
            frame4,
            frame5,
            frame6,
            frame7,
            frame8,
            frame9,
            frame10
        )

        val shockAnimator = ObjectAnimator.ofPropertyValuesHolder(
            containerLayout,
            outLeft,
            outTop,
            shockPropertyValuesHolder
        )

        transition1.setAnimator(
            LayoutTransition.CHANGE_DISAPPEARING,
            shockAnimator
        )

        transition1.addTransitionListener(object : TransitionListener{
            override fun startTransition(
                transition: LayoutTransition?,
                container: ViewGroup?,
                view: View?,
                transitionType: Int
            ) {
                Log.e("LayoutAnimator" , "transitionType : ${transitionType}")
                Log.e("LayoutAnimator" , "count : ${container?.childCount}")
                Log.e("LayoutAnimator" , "view : ${view?.javaClass?.name}")

            }

            override fun endTransition(
                transition: LayoutTransition?,
                container: ViewGroup?,
                view: View?,
                transitionType: Int
            ) {
                Log.e("LayoutAnimator" , "transitionType : ${transitionType}")
                Log.e("LayoutAnimator" , "count : ${container?.childCount}")
                Log.e("LayoutAnimator" , "view : ${view?.javaClass?.name}")
            }
        })


        containerLayout.layoutTransition = transition1

        addButton.setOnClickListener{
            addButton()
        }

        deleteButton.setOnClickListener {
            removeButton()
        }
    }




    private fun addButton(){
        val button = Button(this)
        button.text = "按钮${count}"
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        button.layoutParams = layoutParams
        containerLayout.addView(button , 0)
        // 数量加1
        count++
    }

    private fun removeButton(){
        if(count > 0){
            containerLayout.removeViewAt(0)
            count--
        }
    }

}