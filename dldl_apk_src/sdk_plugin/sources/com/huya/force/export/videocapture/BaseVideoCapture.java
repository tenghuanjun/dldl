package com.huya.force.export.videocapture;

import android.graphics.SurfaceTexture;
import com.huya.force.common.VideoFrameData;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseVideoCapture {
    private static final String TAG = "BaseVideoCapture";
    protected Listener mListener;
    protected boolean mStarted = false;
    private VideoCaptureInput mVideoCaptureInput;

    public interface Listener {
        void onFrameAvailable(VideoFrameData videoFrameData);

        void onStartResult(VideoCaptureStartResult videoCaptureStartResult);
    }

    public enum VideoCaptureStartResult {
        kStartSuccess,
        kStartError
    }

    public abstract SurfaceTexture getPreviewSurfaceTexture();

    public void start() {
    }

    public void stop() {
    }

    public BaseVideoCapture(VideoCaptureInput videoCaptureInput) {
        this.mVideoCaptureInput = videoCaptureInput;
    }

    public VideoCaptureInput getInput() {
        return this.mVideoCaptureInput;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }
}
