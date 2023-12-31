package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlin.concurrent.thread

class SurfaceTextView:  SurfaceView{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    init {
        holder.addCallback(object : SurfaceHolder.Callback{
            /**
             * This is called immediately after the surface is first created.
             * Implementations of this should start up whatever rendering code
             * they desire.  Note that only one thread can ever draw into
             * a [Surface], so you should not draw into the Surface here
             * if your normal rendering will be in another thread.
             *
             * @param holder The SurfaceHolder whose surface is being created.
             */
            override fun surfaceCreated(holder: SurfaceHolder) {
                drawText1()
            }

            /**
             * This is called immediately after any structural changes (format or
             * size) have been made to the surface.  You should at this point update
             * the imagery in the surface.  This method is always called at least
             * once, after [.surfaceCreated].
             *
             * @param holder The SurfaceHolder whose surface has changed.
             * @param format The new [PixelFormat] of the surface.
             * @param width The new width of the surface.
             * @param height The new height of the surface.
             */
            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            /**
             * This is called immediately before a surface is being destroyed. After
             * returning from this call, you should no longer try to access this
             * surface.  If you have a rendering thread that directly accesses
             * the surface, you must ensure that thread is no longer touching the
             * Surface before returning from this function.
             *
             * @param holder The SurfaceHolder whose surface is being destroyed.
             */
            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }
        })
    }

    private val mInts: MutableList<Int> = mutableListOf()

    private fun drawText(){
        thread {


            //先进行清屏操作
            while (true) {
                val dirtyRect = Rect(0, 0, 1, 1)
                val canvas = holder.lockCanvas(dirtyRect)
                val canvasRect = canvas.clipBounds
                if (width == canvasRect.width() && height == canvasRect.height()) {
                    canvas.drawColor(Color.WHITE)
                    holder.unlockCanvasAndPost(canvas)
                } else {
                    holder.unlockCanvasAndPost(canvas)
                    break
                }
            }
            for(i in 0 until 10){
                val  canvas = holder.lockCanvas()
                canvas.drawColor(Color.WHITE)
                mInts.add(i)
                if(canvas != null){
                    for(num in mInts){
                        canvas.drawText(
                            "${num}" , num * 30f , 50f, Paint().apply {
                                textSize = 30f
                                color = Color.RED
                            }
                        )
                    }
                }
                holder.unlockCanvasAndPost(canvas)
                kotlin.runCatching {
                    Thread.sleep(800)
                }

            }

        }
    }

    private fun drawText1(){
        thread {
            //先进行清屏操作
            while (true) {
                val dirtyRect = Rect(0, 0, 1, 1)
                val canvas = holder.lockCanvas(dirtyRect)
                val canvasRect = canvas.clipBounds
                if (width == canvasRect.width() && height == canvasRect.height()) {
                    canvas.drawColor(Color.WHITE)
                    holder.unlockCanvasAndPost(canvas)
                } else {
                    holder.unlockCanvasAndPost(canvas)
                    break
                }
            }

            for(i in 0 until 10){
                val item = 50
                val rect = Rect(
                    i * item,
                    0,
                    (i + 1) * item - 10,
                    item
                )
                val  canvas = holder.lockCanvas(rect)
                if(canvas != null){
                    canvas.apply {
                        drawColor(Color.GREEN)
                        drawText(
                            "${i}",
                            (i * item + 10).toFloat(),
                            (item / 2 + 10).toFloat(),
                            Paint().apply {
                                textSize = 30f
                                color = Color.RED
                            }
                        )
                    }
                }
                holder.unlockCanvasAndPost(canvas)
                kotlin.runCatching {
                    Thread.sleep(800)
                }

            }
        }
    }
}