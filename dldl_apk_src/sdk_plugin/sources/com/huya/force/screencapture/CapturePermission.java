package com.huya.force.screencapture;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import com.huya.force.log.ForceLog;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CapturePermission {
    private static final int QUERY_SCREEN_RECORD = 10001;
    private static final String TAG = "CapturePermission";
    private Listener mListener;
    private MediaProjection mMediaProjection;
    private MediaProjectionManager mMediaProjectionManager;
    private int mResultCode = 0;
    private Intent mResultData = null;
    private final CaptureCallback mCaptureCallback = new CaptureCallback();

    public interface Listener {
        void onCapturePermissionResult(MediaProjection mediaProjection);
    }

    private static class CaptureCallback extends MediaProjection.Callback {
        private WeakReference<CapturePermission> mWrapper;

        private CaptureCallback(CapturePermission capturePermission) {
            this.mWrapper = new WeakReference<>(capturePermission);
        }

        @Override // android.media.projection.MediaProjection.Callback
        public void onStop() {
            super.onStop();
            if (this.mWrapper.get() != null) {
                this.mWrapper.get().onMediaProjectionStop();
            }
        }
    }

    public CapturePermission(Listener listener) {
        this.mListener = listener;
    }

    public void queryCapture(Activity activity) {
        if (this.mMediaProjectionManager == null) {
            this.mMediaProjectionManager = (MediaProjectionManager) activity.getApplicationContext().getSystemService("media_projection");
        }
        if (this.mMediaProjection != null) {
            onCapturePermissionResult();
            return;
        }
        if (this.mResultCode != 0 && this.mResultData != null) {
            setUpMediaProjection();
            onCapturePermissionResult();
            return;
        }
        ForceLog.info(TAG, "queryCapture startActivityForResult");
        try {
            activity.startActivityForResult(this.mMediaProjectionManager.createScreenCaptureIntent(), 10001);
        } catch (Exception e) {
            ForceLog.error(TAG, String.format("queryCapture Exception e=%s", e.toString()));
            e.printStackTrace();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 10001) {
            return;
        }
        ForceLog.info(TAG, String.format("onActivityResult resultCode=%d", Integer.valueOf(i2)));
        this.mResultCode = i2;
        this.mResultData = intent;
        setUpMediaProjection();
        MediaProjection mediaProjection = this.mMediaProjection;
        if (mediaProjection == null) {
            ForceLog.error(TAG, "media projection is null");
        } else {
            mediaProjection.registerCallback(this.mCaptureCallback, null);
            onCapturePermissionResult();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMediaProjectionStop() {
        MediaProjection mediaProjection = this.mMediaProjection;
        if (mediaProjection != null) {
            mediaProjection.unregisterCallback(this.mCaptureCallback);
        }
    }

    public MediaProjection getMediaProjection() {
        return this.mMediaProjection;
    }

    private void setUpMediaProjection() {
        this.mMediaProjection = this.mMediaProjectionManager.getMediaProjection(this.mResultCode, this.mResultData);
    }

    private void onCapturePermissionResult() {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onCapturePermissionResult(this.mMediaProjection);
        }
    }

    public void stop() {
        MediaProjection mediaProjection = this.mMediaProjection;
        if (mediaProjection != null) {
            mediaProjection.stop();
            this.mMediaProjection = null;
        }
    }
}
