package com.huya.force.export.upload;

import com.huya.force.common.AudioData;
import com.huya.force.common.VideoData;
import com.huya.force.export.upload.UploadInput;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseUpload {

    public interface Listener {
        void onUploadResult(int i);
    }

    public interface OnFlowControlListener {
        void onFlowControl(int i);
    }

    public abstract void init();

    public abstract boolean isConnected();

    public abstract void sendAudioData(AudioData audioData);

    public abstract void sendVideoData(VideoData videoData);

    public abstract void setAudioParam(UploadInput.AudioParam audioParam);

    public abstract void setListener(Listener listener);

    public abstract void setVideoHeader(byte[] bArr);

    public abstract void setVideoParam(UploadInput.VideoParam videoParam);

    public abstract void start();

    public abstract void stop();

    public abstract void uninit();

    public BaseUpload(UploadInput uploadInput) {
    }
}
