package me.sargunvohra.android.diningcourts.component.fabtoolbar

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import com.bowyer.app.fabtoolbar.FabToolbar

@Suppress("UNUSED", "UNUSED_PARAMETER")
class FabToolbarScrollBehavior(ctx: Context, attributeSet: AttributeSet) : CoordinatorLayout.Behavior<FabToolbar>() {

    val threshold = 10

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FabToolbar, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        if (dyConsumed > threshold) {
            child.slideOutFab()
        } else if (dyConsumed < -threshold) {
            child.slideInFab()
        }
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FabToolbar, directTargetChild: View, target: View, nestedScrollAxes: Int): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }
}