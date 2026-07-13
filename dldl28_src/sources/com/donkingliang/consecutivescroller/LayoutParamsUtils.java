package com.donkingliang.consecutivescroller;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public class LayoutParamsUtils {
    public static void invalidTopAndBottomMargin(ViewGroup.MarginLayoutParams params) {
        if (params != null) {
            params.topMargin = 0;
            params.bottomMargin = 0;
        }
    }
}
