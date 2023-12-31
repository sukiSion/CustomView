package com.example.customview.volumetwo.six

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewConfiguration
import android.widget.FrameLayout.LayoutParams
import android.widget.LinearLayout
import android.widget.TextView
import com.example.customview.R
import com.example.customview.databinding.ActivitySlideMenuBinding

class SlideMenuActivity : AppCompatActivity(), OnClickListener {
    private lateinit var activitySlideMenuBinding: ActivitySlideMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySlideMenuBinding = ActivitySlideMenuBinding.inflate(layoutInflater)
        setContentView(activitySlideMenuBinding.root)
        activitySlideMenuBinding.smg.setView(
            layoutInflater.inflate(R.layout.layout_content_slide , null ,false),
            LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT),
            initMenuView(),
            LayoutParams(
                resources.getDimensionPixelSize(R.dimen.slide_menu_width),
                LayoutParams.WRAP_CONTENT
            )
        )
    }

    private fun initMenuView(): View{
        return layoutInflater.inflate(R.layout.layout_slide_menu , null , false).also {
            it.findViewById<TextView>(R.id.menu_pear).setOnClickListener(this)
            it.findViewById<TextView>(R.id.menu_apple).setOnClickListener(this)
            it.findViewById<TextView>(R.id.menu_banana).setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.menu_apple -> {
                activitySlideMenuBinding.smg.changeMainViewText("${(v as TextView).text}")
            }
            R.id.menu_banana -> {
                activitySlideMenuBinding.smg.changeMainViewText("${(v as TextView).text}")
            }
            R.id.menu_pear -> {
                activitySlideMenuBinding.smg.changeMainViewText("${(v as TextView).text}")
            }
        }
    }


}