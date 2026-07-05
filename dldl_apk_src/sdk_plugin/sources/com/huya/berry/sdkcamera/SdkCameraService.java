package com.huya.berry.sdkcamera;

import android.app.Activity;
import com.huya.berry.sdkcamera.api.ISdkCameraService;
import com.huya.berry.sdkcamera.event.CameraListener;
import com.huya.live.service.AbsService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SdkCameraService extends AbsService implements ISdkCameraService {
    public static final String TAG = "SdkCameraService";

    @Override // com.huya.berry.sdkcamera.api.ISdkCameraService
    public void showCameraLive(Activity activity, CameraListener cameraListener) {
        CameraEchoActivity.startActivity(activity, cameraListener);
    }
}
