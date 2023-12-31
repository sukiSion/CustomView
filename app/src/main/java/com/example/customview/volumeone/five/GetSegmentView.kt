package com.example.customview.volumeone.five

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class GetSegmentView : View {

    private val paint: Paint
    private val destPath: Path
    private val animator: ValueAnimator
    private val circlePath: Path
    private val pathMeasure: PathMeasure
    private var curValue: Float= 0f
    private val arrawBmp: Bitmap
    private val pos: FloatArray
    private val tan: FloatArray
    private var isNext: Boolean
    private val mCentX = 100f
    private val mCentY = 100f
    private val mRadius = 50f

    init {

        // 取消硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.BLACK
            strokeWidth = 4f
            style = Paint.Style.STROKE
        }
        circlePath = Path().apply {
            addCircle(
                mCentX, mCentY, mRadius, Path.Direction.CW
            )
            moveTo(mCentX - mRadius / 2, mCentY)
            lineTo(mCentX, mCentY + mRadius / 2)
            lineTo(mCentX + mRadius / 2, mCentY - mRadius / 3)
        }
        destPath = Path()
        pathMeasure = PathMeasure(circlePath , false)
        animator = ValueAnimator.ofFloat(0f , 2f).apply {
            addUpdateListener {
                curValue = it.animatedValue as Float
                invalidate()
            }
            duration = 4000
        }
        arrawBmp = BitmapFactory.decodeResource(resources , R.drawable.arraw)

        pos = FloatArray(2)
        tan = FloatArray(2)

        isNext = false
    }

    constructor(context: Context?) : super(context){
        animator.start()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        animator.start()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        animator.start()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.WHITE)
//        val length = pathMeasure.length
//        val stop = length * curValue
//        val start = (stop - ((0.5 - Math.abs(curValue - 0.5)) * length)).toFloat()
//        val start  = if(curValue <= 0.5f){
//            0f * length
//        }else{
//            (2 * curValue -1 ) * length
//        }

//        pathMeasure.getPosTan(stop , pos , tan)
//        val angle = (Math.atan2(tan[1].toDouble() , tan[0].toDouble()) * 180 / Math.PI).toFloat()
//        val newWidth = (arrawBmp.width * 0.2).toFloat()
//        val newHeight = (arrawBmp.height * 0.2).toFloat()
//        val matrix = Matrix().apply {
//            postScale(
//                0.2f,
//                0.2f
//            )
//            postRotate(
//                angle,
//                newWidth / 2 ,
//                newHeight / 2
//            )
//            postTranslate(
//                pos[0] - newWidth / 2 ,
//                pos[1] - newHeight / 2
//            )
//
//        }
//        val matrix = Matrix()
//        pathMeasure.getMatrix(stop  , matrix , PathMeasure.TANGENT_MATRIX_FLAG or PathMeasure.POSITION_MATRIX_FLAG)
//        matrix.preScale(0.2f , 0.2f)
//        matrix.preTranslate(-(arrawBmp.width/ 2).toFloat() , -(arrawBmp.height/ 2).toFloat())
//
//        canvas?.drawBitmap(
//            arrawBmp,
//            matrix,
//            paint
//        )

//        destPath.reset()
//        pathMeasure.getSegment(start , stop , destPath , true)
//        canvas?.drawPath(destPath , paint)

//        curValue.takeIf {
//            it < 1f
//        }?.let {
//            val stop = pathMeasure.length * curValue
//            pathMeasure.getSegment(0f , stop , destPath , true)
//        }
//
//        curValue.takeIf {
//            it >= 1f
//        }?.let {
//            // 当前进度值为1时
//            isNext.takeIf {
//                !it
//            }.apply {
//                pathMeasure.getSegment(0f, pathMeasure.length , destPath , true)
//                pathMeasure.nextContour()
//                isNext = true
//            }
//
//            val stop = pathMeasure.length * (it - 1)
//            pathMeasure.getSegment(0f , stop , destPath , true)
//        }

        if (curValue < 1) {
            val stop: Float = pathMeasure.getLength() * curValue
            pathMeasure.getSegment(0f, stop, destPath, true)
        } else {
            if (!isNext) {
                isNext = true
                pathMeasure.getSegment(0f , pathMeasure.getLength(), destPath, true)
                pathMeasure.nextContour()
            }
            val stop: Float = pathMeasure.getLength() * (curValue - 1)
            pathMeasure.getSegment(0f , stop, destPath, true)
        }
        canvas?.drawPath( destPath, paint)
    }
}