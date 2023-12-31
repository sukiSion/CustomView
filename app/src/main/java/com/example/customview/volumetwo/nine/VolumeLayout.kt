package com.example.customview.volumetwo.nine

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.customview.databinding.LayoutVolumeBinding

class VolumeLayout : FrameLayout {
    constructor(context: Context) : super(context){
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        init(context)
    }

    private lateinit var mVolumeView: VolumeView
    private lateinit var mBinding: LayoutVolumeBinding

    private fun init(context: Context?){
        mBinding = LayoutVolumeBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
        mVolumeView = mBinding.vv
    }

    fun volunmeUp(){
        mVolumeView.volunmeUp()
    }

    fun volumeDown(){
        mVolumeView.volumeDown()
    }


}