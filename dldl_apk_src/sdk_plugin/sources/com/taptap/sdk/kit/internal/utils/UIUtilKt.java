package com.taptap.sdk.kit.internal.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UIUtil.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0005\u001a\n\u0010\u0006\u001a\u00020\u0004*\u00020\u0005\u001a\f\u0010\u0007\u001a\u00020\u0001*\u0004\u0018\u00010\u0002\u001a\f\u0010\b\u001a\u00020\u0001*\u0004\u0018\u00010\u0002¨\u0006\t"}, d2 = {"clearNotFocusable", "", "Landroid/view/Window;", "getScreenHeight", "", "Landroid/content/Context;", "getScreenWidth", "hideNavBar", "setNotFocusable", "tap-common_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class UIUtilKt {
    public static final int getScreenHeight(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static final int getScreenWidth(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static final void hideNavBar(Window window) {
        final View decorView;
        if (window == null || (decorView = window.getDecorView()) == null) {
            return;
        }
        decorView.setSystemUiVisibility(2);
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.taptap.sdk.kit.internal.utils.-$$Lambda$UIUtilKt$O18QDJj25W2JVabqIOcjS7hypsc
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                UIUtilKt.hideNavBar$lambda$1$lambda$0(decorView, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void hideNavBar$lambda$1$lambda$0(View decorView, int i) {
        Intrinsics.checkNotNullParameter(decorView, "$decorView");
        decorView.setSystemUiVisibility(5894);
    }

    public static final void setNotFocusable(Window window) {
        if (window != null) {
            window.setFlags(8, 8);
        }
    }

    public static final void clearNotFocusable(Window window) {
        if (window != null) {
            window.clearFlags(8);
        }
    }
}
