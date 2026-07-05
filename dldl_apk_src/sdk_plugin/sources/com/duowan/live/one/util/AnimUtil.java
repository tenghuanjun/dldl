package com.duowan.live.one.util;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AnimUtil {
    public static void fadeIn(View view, long j, long j2) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setStartOffset(j);
        alphaAnimation.setDuration(j2);
        view.startAnimation(alphaAnimation);
    }

    public static void fadeOut(View view, long j, long j2, Animation.AnimationListener animationListener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setStartOffset(j);
        alphaAnimation.setDuration(j2);
        alphaAnimation.setAnimationListener(animationListener);
        view.startAnimation(alphaAnimation);
    }
}
