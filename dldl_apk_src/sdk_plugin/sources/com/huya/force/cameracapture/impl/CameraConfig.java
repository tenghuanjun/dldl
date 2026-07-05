package com.huya.force.cameracapture.impl;

import android.content.Context;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CameraConfig {
    public int facing;
    public int fps;
    public int height;
    public Object surfaceObject;
    public WeakReference<Context> weakContext;
    public int width;

    public CameraConfig(Context context, int i, int i2, int i3, int i4, Object obj) {
        this.weakContext = new WeakReference<>(context);
        this.facing = i;
        this.width = i2;
        this.height = i3;
        this.fps = i4;
        this.surfaceObject = obj;
    }
}
