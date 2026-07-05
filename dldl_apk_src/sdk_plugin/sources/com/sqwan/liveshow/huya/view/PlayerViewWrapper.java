package com.sqwan.liveshow.huya.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PlayerViewWrapper extends com.huya.berry.sdkplayer.floats.view.PlayerView {
    public PlayerViewWrapper(Context context) {
        super(context);
    }

    public PlayerViewWrapper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PlayerViewWrapper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private boolean isLowVideoViewSdkVersion() {
        return Build.VERSION.SDK_INT <= 23;
    }
}
