package com.example.customview.volumeone.ten

import android.graphics.Bitmap
import android.os.Build

object BitmapUtil{

    fun Bitmap.getBytes(): Int{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            return allocationByteCount
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){
            return byteCount
        }else{
            return rowBytes * height
        }
    }

    fun gc(bitmap: Bitmap?){
        bitmap?.let {
            if(!it.isRecycled){
                it.recycle()
                System.gc()
            }
        }
    }
}


