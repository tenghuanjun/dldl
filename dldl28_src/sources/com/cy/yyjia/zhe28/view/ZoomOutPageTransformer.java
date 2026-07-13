package com.cy.yyjia.zhe28.view;

import android.util.Log;
import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes3.dex */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MAX_SCALE = 1.0f;
    private static final float MIN_SCALE = 0.8f;

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float position) {
        Log.e("transformPage: ", "看看：" + view.toString() + position);
    }
}
