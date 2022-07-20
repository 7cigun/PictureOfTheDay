package ru.gb.pictureoftheday.view.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout


class AlphaBehavior(context: Context, attrs: AttributeSet? = null) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: View,
        ev: MotionEvent
    ): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_CANCEL -> child.alpha = 0.2f
            else -> child.alpha = 1f
        }
        return super.onInterceptTouchEvent(parent, child, ev)
    }
}