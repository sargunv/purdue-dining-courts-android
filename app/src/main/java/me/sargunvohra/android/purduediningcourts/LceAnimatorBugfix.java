/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.sargunvohra.android.purduediningcourts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;

public class LceAnimatorBugfix {

    /**
     * Display the content instead of the loadingView
     */
    @TargetApi(11)
    public static void showContent(final View loadingView, final View contentView,
                                   final View errorView) {

        if (contentView.getVisibility() == View.VISIBLE) {
            // No Changing needed, because contentView is already visible
            errorView.setVisibility(View.GONE);
            loadingView.setVisibility(View.GONE);
        } else {

            if (Build.VERSION.SDK_INT < 14) {
                // Before honeycomb

                loadingView.setVisibility(View.GONE);
                errorView.setVisibility(View.GONE);
                contentView.setVisibility(View.VISIBLE);
            } else {

                // ICS and above

                errorView.setVisibility(View.GONE);

                int translateDp = 40;
                // Not visible yet, so animate the view in
                AnimatorSet set = new AnimatorSet();
                ObjectAnimator contentFadeIn = ObjectAnimator.ofFloat(contentView, "alpha", 0f, 1f);
                ObjectAnimator loadingFadeOut = ObjectAnimator.ofFloat(loadingView, "alpha", 1f, 0f);

                // temporary fix for Lollipop 5.1 bug

                if (Build.VERSION.SDK_INT < 22) {
                    // ICS through Lollipop 5.0

                    ObjectAnimator contentTranslateIn = ObjectAnimator.ofFloat(contentView, "translationY",
                            dpToPx(loadingView.getContext(), translateDp), 0);
                    ObjectAnimator loadingTranslateOut = ObjectAnimator.ofFloat(loadingView, "translationY", 0,
                            -dpToPx(loadingView.getContext(), translateDp));
                    set.playTogether(contentFadeIn, contentTranslateIn, loadingFadeOut, loadingTranslateOut);
                } else {
                    // Lollipop 5.1

                    set.playTogether(contentFadeIn, loadingFadeOut);
                }

                set.setDuration(500);

                set.addListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        contentView.setTranslationY(0);
                        loadingView.setTranslationY(0);
                        contentView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadingView.setVisibility(View.GONE);
                        loadingView.setAlpha(1f); // For future showLoading calls
                        contentView.setTranslationY(0);
                        loadingView.setTranslationY(0);
                    }
                });

                set.start();
            }
        }
    }

    /**
     * Converts a dp value to a px value
     *
     * @param dp the dp value
     */
    public static int dpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }
}