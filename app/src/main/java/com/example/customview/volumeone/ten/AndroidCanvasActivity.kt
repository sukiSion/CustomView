package com.example.customview.volumeone.ten

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.customview.R
import com.example.customview.databinding.ActivityAndroidCanvasBinding
import com.example.customview.volumeone.net.NetWorkClient
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class AndroidCanvasActivity : AppCompatActivity() {
    private lateinit var activityAndroidCanvasBinding: ActivityAndroidCanvasBinding
    private lateinit var launcher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAndroidCanvasBinding = ActivityAndroidCanvasBinding.inflate(layoutInflater)
        setContentView(activityAndroidCanvasBinding.root)

        launcher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
        ){

        }

        launcher.launch(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )

        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }

        val bitmap = BitmapFactory.decodeFile(
            "/storage/emulated/0/Pictures/bili/1686389477426.png",

        )


        val bitmap1 = BitmapFactory.decodeResource(
            resources,
            R.drawable.p3,
            BitmapFactory.Options().apply {
//                inDensity = 1
//                inTargetDensity = 2
                inPreferredConfig = Bitmap.Config.RGB_565
            }
        )


//        Log.e("Bitmap" , "文件bitmap: ${bitmap} width: ${bitmap.width} height: ${bitmap.height} byteCount: ${bitmap.byteCount}")
//        Log.e("Bitmap" , "资源bitmap1: ${bitmap1} width: ${bitmap1.width} height: ${bitmap1.height} byteCount: ${bitmap1.byteCount}")

//        Log.e("Bitmap" , "byteCount: ${bitmap.allocationByteCount}")
//        Log.e("Bitmap" , "byteCount: ${bitmap.byteCount}")
//        Log.e("Bitmap" , "byteCount: ${bitmap.rowBytes * bitmap.height}")

        activityAndroidCanvasBinding.apply {

            downloadImage.setOnClickListener {
                lifecycleScope.launch {
                    val result = NetWorkClient.getService().downloadImage(
                        "media/F0RnKidaQAQuOP-?format=jpg&name=large"
                    )

                    kotlin.runCatching {
                        val byteArray = result.byteStream().readBytes()
                        val length = byteArray.size
                        val bitmap = BitmapFactory.decodeByteArray(
                            byteArray,
                            0,
                            length
                        )
                        runOnUiThread {
                            customDrawableImage.setImageBitmap(bitmap)
                        }
                    }
                }
            }

            shapeButton.setOnClickListener {
                val background = shapeText.background as GradientDrawable
                background.cornerRadius = 30f
            }

            kotlin.runCatching {
                val bitmapStream = FileInputStream(File("/storage/emulated/0/Pictures/bili/1686389477426.png"))
                val newBitmap = BitmapFactory.decodeFileDescriptor(
                    bitmapStream.fd
                )

//                customDrawableImage.setImageBitmap(
//                    newBitmap
//                )
            }
            customDrawableText.background = CustomDrawable(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.p1
                )
            ).apply {
                intrinsicHeight = -1
                intrinsicWidth = -1
            }
        }

//        setDensity()
//        setPixel()
//        bitmapCompress()
        saveImage()
    }

    private fun bitmapCompress(){
        val bitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.cat
        )
        activityAndroidCanvasBinding.densityView1.setImageBitmap(bitmap)

        val outPutStream = ByteArrayOutputStream()
        bitmap.compress(
            Bitmap.CompressFormat.WEBP,
            1,
            outPutStream
        )

        val byteArray = outPutStream.toByteArray()

        val bitmap1 = BitmapFactory.decodeByteArray(
            byteArray,
            0,
            byteArray.size
        )

        activityAndroidCanvasBinding.densityView2.setImageBitmap(bitmap1)

    }
    
    private fun saveImage(){
        activityAndroidCanvasBinding.apply {
            saveImage.setOnClickListener {
                val imagePath = "${application.externalCacheDir}/cat.webp"
                val file = File(imagePath)

                if(file.exists()){
                    file.delete()
                }
                kotlin.runCatching {
                    val fileOutputStream = FileOutputStream(file)
                    BitmapFactory.decodeResource(
                        resources,
                        R.drawable.p3
                    ).apply {
                        compress(
                            Bitmap.CompressFormat.WEBP,
                            1,
                            fileOutputStream
                        )
                    }

                    fileOutputStream.apply {
                        flush()
                        close()
                    }
                }
            }
        }
    }

    private fun setDensity(){
        activityAndroidCanvasBinding.apply {
            val bitmap = BitmapFactory.decodeResource(
                resources,
                R.drawable.cat
            )

            densityView1.setImageBitmap(bitmap)

            bitmap.density = bitmap.density * 2

            densityView2.setImageBitmap(
                bitmap
            )
        }
    }

    private fun setPixel(){
        val bitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.dog
        ).copy(
            Bitmap.Config.ARGB_8888,
            true
        )

        for(y in 0 until  bitmap.height){
            for(x in 0 until bitmap.width){
                val originColor = bitmap.getPixel(x , y)
                val red = Color.red(originColor)
                val alpha = Color.alpha(originColor)
                var green = Color.green(originColor)
                val blue = Color.blue(originColor)

                if(green < 200){
                    green += 30
                }

                bitmap.setPixel(
                    x , y , Color.argb(
                        alpha,
                        red,
                        green,
                        blue
                    )
                )
            }
        }

        activityAndroidCanvasBinding.densityView1.setImageBitmap(bitmap)
    }
}