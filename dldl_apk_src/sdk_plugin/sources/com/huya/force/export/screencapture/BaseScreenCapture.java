package com.huya.force.export.screencapture;

import android.content.Intent;
import com.huya.force.export.videocapture.BaseVideoCapture;
import com.huya.force.export.videocapture.VideoCaptureInput;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseScreenCapture extends BaseVideoCapture {
    public abstract void onActivityResult(int i, int i2, Intent intent);

    public BaseScreenCapture(VideoCaptureInput videoCaptureInput) {
        super(videoCaptureInput);
    }
}
