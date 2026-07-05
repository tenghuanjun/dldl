package com.huya.force.screencapture;

import android.app.Activity;
import android.os.Handler;
import com.huya.force.export.videocapture.VideoCaptureInput;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ScreenCaptureInput extends VideoCaptureInput {
    Activity mActivity;

    public ScreenCaptureInput(Activity activity, int i, int i2, int i3) {
        super(activity.getApplicationContext(), VideoCaptureInput.VideoCaptureType.kScreenCapture, i, i2, i3);
        this.mActivity = activity;
    }

    public ScreenCaptureInput(Activity activity, int i, int i2, int i3, Handler handler) {
        super(activity.getApplicationContext(), VideoCaptureInput.VideoCaptureType.kScreenCapture, i, i2, i3, handler);
        this.mActivity = activity;
    }

    public Activity getActivity() {
        return this.mActivity;
    }
}
