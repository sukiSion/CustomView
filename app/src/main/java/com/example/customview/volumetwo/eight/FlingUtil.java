package com.example.customview.volumetwo.eight;

import android.content.Context;
import android.hardware.SensorManager;
import android.view.ViewConfiguration;

public class FlingUtil {
    private Context context;

    public FlingUtil(Context context){
        this.context = context;
        final float ppi = context.getResources().getDisplayMetrics().density * 160.0f;
        this.mPhysicalCoeff = SensorManager.GRAVITY_EARTH // g (m/s^2)
                * 39.37f // inch/meter
                * ppi
                * 0.84f; // look and feel tuning
    }
    private static final float INFLEXION = 0.35f;
    private static float DECELERATION_RATE = (float) (Math.log(0.78) / Math.log(0.9));
    private float mFlingFriction = ViewConfiguration.getScrollFriction();
    private float mPhysicalCoeff = 0;

    public double getSplineDeceleration( int velocity) {
        return Math.log(INFLEXION * Math.abs(velocity) / (mFlingFriction * mPhysicalCoeff));
    }

    public float getmPhysicalCoeff(){
        return mPhysicalCoeff;
    }

    public double getSplineFlingDistance(int velocity) {
        final double l = getSplineDeceleration(velocity);
        final double decelMinusOne = DECELERATION_RATE - 1.0;
        return mFlingFriction * mPhysicalCoeff * Math.exp(DECELERATION_RATE / decelMinusOne * l);
    }

    public int getVelocity(double distance){
        final double decelMinusOne = DECELERATION_RATE - 1.0;
        double aecel = Math.log(distance / (mFlingFriction * mPhysicalCoeff)) * decelMinusOne / DECELERATION_RATE;
        return Math.abs((int) (Math.exp(aecel) * (mFlingFriction * mPhysicalCoeff) / INFLEXION));
    }
}
