package com.huya.force.export.videocapture;

import android.content.Context;
import android.os.Handler;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VideoCaptureInput {
    WeakReference<Context> mContext;
    Handler mEglHandler;
    int mFps;
    int mHeight;
    VideoCaptureType mVideoCaptureType;
    int mWidth;

    public enum VideoCaptureType {
        kScreenCapture,
        kCameraCapture,
        kCamera2Capture,
        kCustom
    }

    public VideoCaptureInput(Context context, VideoCaptureType videoCaptureType, int i, int i2, int i3, Handler handler) {
        this(context, videoCaptureType, i, i2, i3);
        this.mEglHandler = handler;
    }

    public VideoCaptureInput(Context context, VideoCaptureType videoCaptureType, int i, int i2, int i3) {
        this.mContext = new WeakReference<>(context);
        this.mVideoCaptureType = videoCaptureType;
        this.mWidth = i;
        this.mHeight = i2;
        this.mFps = i3;
    }

    public Context getContext() {
        return this.mContext.get();
    }

    public VideoCaptureType getVideoCaptureType() {
        return this.mVideoCaptureType;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getFps() {
        return this.mFps;
    }

    public Handler getEglHandler() {
        return this.mEglHandler;
    }

    public void setEglHandler(Handler handler) {
        this.mEglHandler = handler;
    }
}
