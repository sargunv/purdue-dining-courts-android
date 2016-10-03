package me.sargunvohra.android.diningcourts.util;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.support.annotation.ColorInt;
import android.support.v7.widget.Toolbar;

public class ToolbarColorAnimator {
    private @ColorInt
    int currentColor;
    private Toolbar toolbar;

    public ToolbarColorAnimator(Toolbar toolbar, @ColorInt int startingColor) {
        this.toolbar = toolbar;
        this.currentColor = startingColor;
        applyColor(currentColor);
    }

    private void applyColor(@ColorInt int newColor) {
        toolbar.setBackgroundColor(newColor);
    }

    public void animateToColor(@ColorInt int newColor) {
        ValueAnimator anim = ValueAnimator.ofObject(new ArgbEvaluator(), currentColor, newColor);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (Integer) animation.getAnimatedValue();
                currentColor = color;
                applyColor(color);
            }
        });
        anim.start();
    }
}
