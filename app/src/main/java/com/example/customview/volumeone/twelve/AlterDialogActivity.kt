package com.example.customview.volumeone.twelve

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.customview.R
import com.example.customview.databinding.ActivityAlterDialogBinding

class AlterDialogActivity : AppCompatActivity() , View.OnTouchListener{
    private lateinit var activityAlterDialogBinding: ActivityAlterDialogBinding

    private  var  mImageView: ImageView? = null
    private lateinit var mWindowManager: WindowManager
    private lateinit var mLayoutParams: LayoutParams


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAlterDialogBinding = ActivityAlterDialogBinding.inflate(layoutInflater)
        setContentView(activityAlterDialogBinding.root)

        activityAlterDialogBinding.getPermission.setOnClickListener {
            startActivityForResult(
                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION),
                1
            )
        }

        mWindowManager = application.getSystemService(Context.WINDOW_SERVICE) as WindowManager



        activityAlterDialogBinding.addView.setOnClickListener {
            mImageView = ImageView(this).apply {
                setBackgroundResource(R.mipmap.ic_launcher)
            }

            mLayoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
                2000,
                LayoutParams.FLAG_NOT_FOCUSABLE or LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSPARENT
            ).apply {
                type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    LayoutParams.TYPE_APPLICATION_OVERLAY
                } else {
                    LayoutParams.TYPE_SYSTEM_ALERT
                }
                gravity = Gravity.TOP or Gravity.START
                x = 0
                y = 300
            }

            mImageView?.setOnTouchListener(this@AlterDialogActivity)

            windowManager.addView(mImageView , mLayoutParams)
        }

        activityAlterDialogBinding.removeView.setOnClickListener {
            mImageView?.let {
                windowManager.removeView(it)
            }
        }

        Log.e("Sion" , "${ ContextCompat.checkSelfPermission(
            this , Manifest.permission.SYSTEM_ALERT_WINDOW
        )  == PackageManager.PERMISSION_GRANTED}")

        launcher.launch(arrayOf(
            Manifest.permission.SYSTEM_ALERT_WINDOW,
        ))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("Sion" , "requestCode: ${requestCode} , resultCode: ${resultCode} , data: ${data}")
        if(requestCode == 1){

        }
    }

    var rawX = 0
    var rawY = 0

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.let {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    rawX = it.rawX.toInt()
                    rawY = it.rawY.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    mLayoutParams.x += it.rawX.toInt() - rawX
                    mLayoutParams.y += it.rawY.toInt() - rawY
                    mWindowManager.updateViewLayout(mImageView, mLayoutParams)
                    rawX = it.rawX.toInt()
                    rawY = it.rawY.toInt()
                }
                else -> {}
            }
        }
        return false
    }

    private val launcher by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            Log.e("Sion" , "${it}")
        }
    }
}