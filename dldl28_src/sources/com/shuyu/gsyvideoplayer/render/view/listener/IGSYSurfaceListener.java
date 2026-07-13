package com.shuyu.gsyvideoplayer.render.view.listener;

import android.view.Surface;

/* JADX INFO: loaded from: classes3.dex */
public interface IGSYSurfaceListener {
    void onSurfaceAvailable(Surface surface);

    boolean onSurfaceDestroyed(Surface surface);

    void onSurfaceSizeChanged(Surface surface, int i, int i2);

    void onSurfaceUpdated(Surface surface);
}
