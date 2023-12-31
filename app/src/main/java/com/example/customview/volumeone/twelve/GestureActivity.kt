package com.example.customview.volumeone.twelve

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.customview.databinding.ActivityGestureBinding
import kotlin.math.abs


class GestureActivity: AppCompatActivity() {

    private lateinit var activityGestureBinding: ActivityGestureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityGestureBinding = ActivityGestureBinding.inflate(layoutInflater)
        setContentView(activityGestureBinding.root)
        activityGestureBinding.gestureImage.setOnTouchListener { v, event ->
            simpleGesturor.onTouchEvent(event)
        }

    }



    companion object{
        const val FLING_MIN_DISTANCE = 100
        const val FLING_MIN_VELOCITY = 200
    }

    private val simpleGesturor by lazy {
        GestureDetector(
            this,
            object : SimpleOnGestureListener(){
                override fun onDown(e: MotionEvent): Boolean {
                    return true
                }

                override fun onFling(
                    e1: MotionEvent,
                    e2: MotionEvent,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {

                    // 左滑
                    if(e1.x - e2.x > FLING_MIN_DISTANCE && abs(velocityX) > FLING_MIN_VELOCITY){
                        Log.e("Sion" , "left Fling")

                    }
                    // 右滑
                    else if(e2.x - e1.x > FLING_MIN_DISTANCE && abs(velocityX) > FLING_MIN_VELOCITY){
                        Log.e("Sion" , "right Fling")
                    }

                    return super.onFling(e1, e2, velocityX, velocityY)
                }
            }
        )
    }

    private val gestureDetector by lazy {
        val listener = object : OnGestureListener{
            /**
             * Notified when a tap occurs with the down [MotionEvent]
             * that triggered it. This will be triggered immediately for
             * every down event. All other events should be preceded by this.
             *
             * @param e The down motion event.
             */
            override fun onDown(e: MotionEvent): Boolean {
                Log.e("Sion" , "onDown")
                return true
            }

            /**
             * The user has performed a down [MotionEvent] and not performed
             * a move or up yet. This event is commonly used to provide visual
             * feedback to the user to let them know that their action has been
             * recognized i.e. highlight an element.
             *
             * @param e The down motion event
             */
            override fun onShowPress(e: MotionEvent) {
                Log.e("Sion" , "onShowPress")
            }

            /**
             * Notified when a tap occurs with the up [MotionEvent]
             * that triggered it.
             *
             * @param e The up motion event that completed the first tap
             * @return true if the event is consumed, else false
             */
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                Log.e("Sion" , "onSingleTapUp")
                return true
            }

            /**
             * Notified when a scroll occurs with the initial on down [MotionEvent] and the
             * current move [MotionEvent]. The distance in x and y is also supplied for
             * convenience.
             *
             * @param e1 The first down motion event that started the scrolling.
             * @param e2 The move motion event that triggered the current onScroll.
             * @param distanceX The distance along the X axis that has been scrolled since the last
             * call to onScroll. This is NOT the distance between `e1`
             * and `e2`.
             * @param distanceY The distance along the Y axis that has been scrolled since the last
             * call to onScroll. This is NOT the distance between `e1`
             * and `e2`.
             * @return true if the event is consumed, else false
             */
            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                Log.e("Sion" , "onScroll")
                return true
            }

            /**
             * Notified when a long press occurs with the initial on down [MotionEvent]
             * that trigged it.
             *
             * @param e The initial on down motion event that started the longpress.
             */
            override fun onLongPress(e: MotionEvent) {
                Log.e("Sion" , "onLongPress")
            }

            /**
             * Notified of a fling event when it occurs with the initial on down [MotionEvent]
             * and the matching up [MotionEvent]. The calculated velocity is supplied along
             * the x and y axis in pixels per second.
             *
             * @param e1 The first down motion event that started the fling.
             * @param e2 The move motion event that triggered the current onFling.
             * @param velocityX The velocity of this fling measured in pixels per second
             * along the x axis.
             * @param velocityY The velocity of this fling measured in pixels per second
             * along the y axis.
             * @return true if the event is consumed, else false
             */
            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                Log.e("Sion" , "onFling")
                return true
            }
        }
        GestureDetector(this , listener).apply {
            setOnDoubleTapListener(object : GestureDetector.OnDoubleTapListener{
                /**
                 * Notified when a single-tap occurs.
                 *
                 *
                 * Unlike [OnGestureListener.onSingleTapUp], this
                 * will only be called after the detector is confident that the user's
                 * first tap is not followed by a second tap leading to a double-tap
                 * gesture.
                 *
                 * @param e The down motion event of the single-tap.
                 * @return true if the event is consumed, else false
                 */
                override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                    Log.e("Sion" , "onSingleTapConfirmed")
                    return true
                }

                /**
                 * Notified when a double-tap occurs. Triggered on the down event of second tap.
                 *
                 * @param e The down motion event of the first tap of the double-tap.
                 * @return true if the event is consumed, else false
                 */
                override fun onDoubleTap(e: MotionEvent): Boolean {
                    Log.e("Sion" , "onDoubleTap")
                    return true
                }

                /**
                 * Notified when an event within a double-tap gesture occurs, including
                 * the down, move, and up events.
                 *
                 * @param e The motion event that occurred during the double-tap gesture.
                 * @return true if the event is consumed, else false
                 */
                override fun onDoubleTapEvent(e: MotionEvent): Boolean {
                    Log.e("Sion" , "onDoubleTapEvent: ${e.action}")
                    return true
                }
            })
        }

    }
}