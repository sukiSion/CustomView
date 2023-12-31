package com.example.customview.volumetwo.two

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout

class FoldDrawLayout: DrawerLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        for(i in 0 until childCount){
            val child = getChildAt(i)
            if(isDrawerView(child)) {
                val layoutParams = child.layoutParams as LayoutParams
                val polyToPolyLayout = PolyToPolyLayout(context)
                removeView(child)
                polyToPolyLayout.addView(child)
                addView(polyToPolyLayout , i , layoutParams)
            }
        }

        addDrawerListener(object : DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                if(drawerView is PolyToPolyLayout){
                    drawerView.setFactor(factor = slideOffset)
                }
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {
            }

            override fun onDrawerStateChanged(newState: Int) {
            }
        })
    }

    fun isDrawerView(child: View): Boolean{
        val gravity = (child.layoutParams as LayoutParams).gravity
        val absGravity = GravityCompat.getAbsoluteGravity(gravity , ViewCompat.getLayoutDirection(child))
        return (absGravity and ( Gravity.START or Gravity.END )) != 0
    }
}