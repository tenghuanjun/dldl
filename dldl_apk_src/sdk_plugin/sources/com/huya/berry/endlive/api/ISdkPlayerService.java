package com.huya.berry.endlive.api;

import android.app.Activity;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ISdkPlayerService {
    boolean hideDanmuView(boolean z);

    void init();

    void openFloatEdit(Activity activity);

    void openLine(Activity activity);

    void openRealFloat();

    boolean showDanmuView(FrameLayout frameLayout, long j);

    void watchLive();
}
