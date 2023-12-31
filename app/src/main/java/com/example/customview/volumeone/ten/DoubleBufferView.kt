package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlin.concurrent.thread

class DoubleBufferView: SurfaceView {
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

    private val mPaint by lazy {
        Paint().apply {
            color = Color.RED
            textSize = 30f
        }
    }

    init {
        holder.addCallback(
            object : SurfaceHolder.Callback{
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
                    drawText()
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
            }
        )
    }

    private fun drawText(){
        thread {
            for(i in 0 until 10){
                val canvas  = holder.lockCanvas()
                if(canvas != null){
                    canvas.drawText(
                        "${i}",
                        i * 30f ,
                        50f,
                        mPaint
                    )
                }
                holder.unlockCanvasAndPost(canvas)
                kotlin.runCatching {
                    Thread.sleep(800)
                }
            }
        }
    }
}