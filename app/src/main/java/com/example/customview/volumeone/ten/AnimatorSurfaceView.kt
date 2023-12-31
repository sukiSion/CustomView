package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Xfermode
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.customview.R

import kotlin.concurrent.thread
class AnimatorSurfaceView: SurfaceView {
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

    private var flag = false
    private val bitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(
            resources,
            R.drawable.p2
        )
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
                    Log.e("Surface" , "width: ${width} height: ${height}")
                    startAnimator()
                    flag = true
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
                    flag = false
                }
            }
        )
    }

    sealed interface State{
        object Start: State
        object End: State
    }

    private fun startAnimator(){
        val scale:Float = height.toFloat() / bitmap.height
        val scaledWidth:Float = bitmap.width.toFloat() * scale

        val scaledBitmap = Bitmap.createScaledBitmap(
            bitmap,
            scaledWidth.toInt(),
            height,
            true
        )

        thread(
            start = true
        ) {
            while(flag){
                val canvas = holder.lockCanvas()
                if(canvas != null){
                    drawView(scaledBitmap , canvas)
                }
                holder.unlockCanvasAndPost(canvas)
                kotlin.runCatching {
                    Thread.sleep(1)
                }
            }
        }
    }

    private var translateX = 0f
    private var state: State = State.End

    private fun drawView(bitmap: Bitmap , canvas: Canvas){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        canvas.drawBitmap(
            bitmap,
            translateX,
            0f,
            null
        )

        /**
         * 清屏函数
         */
        canvas.drawPaint(
            Paint().apply {
                xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            }
        )

        when(state){
            is State.End -> {
                translateX -= 1
            }
            is State.Start -> {
                translateX += 1
            }
        }

        if(translateX <= - (bitmap.width - width)){
            state = State.Start
        }
        if(translateX >= 0){
            state = State.End
        }
    }
}