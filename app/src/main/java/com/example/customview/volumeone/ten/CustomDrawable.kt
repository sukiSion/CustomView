package com.example.customview.volumeone.ten

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.Drawable

class CustomDrawable(val bitmap: Bitmap): Drawable() {

    private val mPaint:Paint

    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
    }

    private var mBound: RectF? = null
    private var intrinsicHeight: Int? = null
    private var intrinsicWidth: Int? = null

    fun setIntrinsicHeight(height: Int){
        intrinsicHeight = height
    }

    fun setIntrinsicWidth(width: Int){
        intrinsicWidth = width
    }


    /**
     * Draw in its bounds (set via setBounds) respecting optional effects such
     * as alpha (set via setAlpha) and color filter (set via setColorFilter).
     *
     * @param canvas The canvas to draw into
     */
    override fun draw(canvas: Canvas) {
        mBound?.apply {
            canvas.drawRoundRect(
                this,
                50f,
                50f,
                mPaint
            )
        }
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        val scaleBitmap = Bitmap.createScaledBitmap(
            bitmap,
            right - left,
            bottom - top,
            true
        )
        mPaint.shader = BitmapShader(
            scaleBitmap,
            Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
        mBound = RectF(
            left.toFloat(),
            top.toFloat(),
            right.toFloat(),
            bottom.toFloat()
        )
    }



    override fun getIntrinsicHeight(): Int {
        return intrinsicHeight ?: bitmap.height
    }

    override fun getIntrinsicWidth(): Int {
        return intrinsicWidth ?: bitmap.width
    }

    /**
     * Specify an alpha value for the drawable. 0 means fully transparent, and
     * 255 means fully opaque.
     */
    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    /**
     * Specify an optional color filter for the drawable.
     *
     *
     * If a Drawable has a ColorFilter, each output pixel of the Drawable's
     * drawing contents will be modified by the color filter before it is
     * blended onto the render target of a Canvas.
     *
     *
     *
     * Pass `null` to remove any existing color filter.
     *
     *
     * **Note:** Setting a non-`null` color
     * filter disables [tint][.setTintList].
     *
     *
     * @param colorFilter The color filter to apply, or `null` to remove the
     * existing color filter
     */
    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    /**
     * Return the opacity/transparency of this Drawable.  The returned value is
     * one of the abstract format constants in
     * [android.graphics.PixelFormat]:
     * [android.graphics.PixelFormat.UNKNOWN],
     * [android.graphics.PixelFormat.TRANSLUCENT],
     * [android.graphics.PixelFormat.TRANSPARENT], or
     * [android.graphics.PixelFormat.OPAQUE].
     *
     *
     * An OPAQUE drawable is one that draws all all content within its bounds, completely
     * covering anything behind the drawable. A TRANSPARENT drawable is one that draws nothing
     * within its bounds, allowing everything behind it to show through. A TRANSLUCENT drawable
     * is a drawable in any other state, where the drawable will draw some, but not all,
     * of the content within its bounds and at least some content behind the drawable will
     * be visible. If the visibility of the drawable's contents cannot be determined, the
     * safest/best return value is TRANSLUCENT.
     *
     *
     * Generally a Drawable should be as conservative as possible with the
     * value it returns.  For example, if it contains multiple child drawables
     * and only shows one of them at a time, if only one of the children is
     * TRANSLUCENT and the others are OPAQUE then TRANSLUCENT should be
     * returned.  You can use the method [.resolveOpacity] to perform a
     * standard reduction of two opacities to the appropriate single output.
     *
     *
     * Note that the returned value does not necessarily take into account a
     * custom alpha or color filter that has been applied by the client through
     * the [.setAlpha] or [.setColorFilter] methods. Some subclasses,
     * such as [BitmapDrawable], [ColorDrawable], and [GradientDrawable],
     * do account for the value of [.setAlpha], but the general behavior is dependent
     * upon the implementation of the subclass.
     *
     * @return int The opacity class of the Drawable.
     *
     * @see android.graphics.PixelFormat
     */
    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

}