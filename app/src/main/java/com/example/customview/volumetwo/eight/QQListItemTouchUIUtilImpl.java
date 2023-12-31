package com.example.customview.volumetwo.eight;

import android.graphics.Canvas;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchUIUtil;
import androidx.recyclerview.widget.RecyclerView;

public class QQListItemTouchUIUtilImpl implements ItemTouchUIUtil {
    static final ItemTouchUIUtil INSTANCE =  new QQListItemTouchUIUtilImpl();
    @Override
    public void onDraw(
            @NonNull Canvas c,
            @NonNull RecyclerView recyclerView,
            @NonNull View view,
            float dX,
            float dY,
            int actionState,
            boolean isCurrentlyActive
    ) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (isCurrentlyActive) {
                Object originalElevation = view.getTag(androidx.recyclerview.R.id.item_touch_helper_previous_elevation);
                if (originalElevation == null) {
                    originalElevation = ViewCompat.getElevation(view);
                    float newElevation = 1f + findMaxElevation(recyclerView, view);
                    ViewCompat.setElevation(view, newElevation);
                    view.setTag(androidx.recyclerview.R.id.item_touch_helper_previous_elevation, originalElevation);
                }
            }
        }

        view.setTranslationX(dX);
        view.setTranslationY(dY);
    }

    private static float findMaxElevation(RecyclerView recyclerView, View itemView) {
        final int childCount = recyclerView.getChildCount();
        float max = 0;
        for (int i = 0; i < childCount; i++) {
            final View child = recyclerView.getChildAt(i);
            if (child == itemView) {
                continue;
            }
            final float elevation = ViewCompat.getElevation(child);
            if (elevation > max) {
                max = elevation;
            }
        }
        return max;
    }

    @Override
    public void onDrawOver(
            @NonNull Canvas c,
            @NonNull RecyclerView recyclerView,
            @NonNull View view,
            float dX,
            float dY,
            int actionState,
            boolean isCurrentlyActive
    ) {
    }

    @Override
    public void clearView(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            final Object tag = view.getTag(androidx.recyclerview.R.id.item_touch_helper_previous_elevation);
            if (tag instanceof Float) {
                ViewCompat.setElevation(view, (Float) tag);
            }
            view.setTag(androidx.recyclerview.R.id.item_touch_helper_previous_elevation, null);
        }

        view.setTranslationX(0f);
        view.setTranslationY(0f);
    }

    @Override
    public void onSelected(@NonNull View view) {
    }
}
