package com.example.customview.volumeone.ten

import android.content.Context
import android.graphics.BlendMode
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlin.concurrent.thread

class SurfaceGesturePathView: SurfaceView {
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

    private val paint by lazy {
        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 5f
            color = Color.RED
            isAntiAlias = true
            strokeCap = Paint.Cap.ROUND
        }
    }

    init {
//        setLayerType(LAYER_TYPE_SOFTWARE , null)
//        setWillNotDraw(false)
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


    private val path by lazy {
        Path()
    }

    private var mDx = 0f
    private var mDy = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        event?.apply {
            when(action){
                MotionEvent.ACTION_DOWN -> {
                    path.moveTo(
                        x , y
                    )
                    mDx = x
                    mDy = y
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    path.quadTo(
                        mDx,
                        mDy,
                        (x + mDx) / 2,
                        (y + mDy) / 2
                    )
                    mDx = x
                    mDy = y
                }
            }
        }

        thread(
            start = true
        ) {
            val canvas = holder.lockCanvas()
            if(canvas != null){
//            canvas.drawColor(Color.RED)
                canvas.drawPath(
                    path, paint
                )
            }
            holder.unlockCanvasAndPost(canvas)
        }

        Log.e("${SurfaceGesturePathView::class.java.simpleName}" , "postInvalidate")
        return super.onTouchEvent(event)

    }

//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        Log.e("${SurfaceGesturePathView::class.java.simpleName}" , "onDraw")
//        canvas?.drawColor(Color.TRANSPARENT , PorterDuff.Mode.CLEAR)
//        canvas?.drawPath(
//            path, paint
//        )
//    }
}