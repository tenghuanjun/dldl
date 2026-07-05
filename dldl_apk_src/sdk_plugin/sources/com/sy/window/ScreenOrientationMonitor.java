package com.sy.window;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
final class ScreenOrientationMonitor implements ComponentCallbacks {
    private OnScreenOrientationCallback mCallback;
    private Context mContext;
    private int mScreenOrientation;

    interface OnScreenOrientationCallback {

        /* JADX INFO: renamed from: com.sy.window.ScreenOrientationMonitor$OnScreenOrientationCallback$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onScreenOrientationChange(OnScreenOrientationCallback onScreenOrientationCallback, int i) {
            }
        }

        void onScreenOrientationChange(int i);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    public ScreenOrientationMonitor(Configuration configuration) {
        this.mScreenOrientation = configuration.orientation;
    }

    void registerCallback(Context context, OnScreenOrientationCallback onScreenOrientationCallback) {
        context.registerComponentCallbacks(this);
        this.mCallback = onScreenOrientationCallback;
        this.mContext = context;
    }

    void unregisterCallback(Context context) {
        context.unregisterComponentCallbacks(this);
        this.mCallback = null;
        this.mContext = null;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (this.mScreenOrientation == this.mContext.getResources().getConfiguration().orientation) {
            return;
        }
        int i = this.mContext.getResources().getConfiguration().orientation;
        this.mScreenOrientation = i;
        OnScreenOrientationCallback onScreenOrientationCallback = this.mCallback;
        if (onScreenOrientationCallback == null) {
            return;
        }
        onScreenOrientationCallback.onScreenOrientationChange(i);
    }
}
