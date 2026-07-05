package com.huya.force.rtmpupload;

import com.huya.force.common.AudioData;
import com.huya.force.common.VideoData;
import com.huya.force.export.upload.BaseUpload;
import com.huya.force.export.upload.IUploadStatics;
import com.huya.force.export.upload.UploadInput;
import com.huya.force.log.ForceLog;
import com.huya.force.rtmpupload.RtmpUploadInput;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RtmpUpload extends BaseUpload implements IUploadStatics, BaseUpload.Listener {
    private static final String TAG = "RtmpUpload";
    private BaseUpload.Listener mListener;
    private long mNativeObjectPtr;
    RtmpUploadInput mRtmpUploadInput;

    public enum RtmpResult {
        kConnectSuccess,
        kFirstVideoAck,
        kFirstAudioAck,
        kBadUrl,
        kConnectError,
        kInvalidStream,
        kWaitTimeout,
        kSendError
    }

    private native int GetNetState(long j);

    private native long GetTotalSendAudioBytes(long j);

    private native int GetTotalSendAudioFrames(long j);

    private native int GetTotalSendBlockCount(long j);

    private native int GetTotalSendBytes(long j);

    private native int GetTotalSendTime(long j);

    private native long GetTotalSendVideoBytes(long j);

    private native int GetTotalSendVideoFrames(long j);

    private native long Init();

    private native boolean IsConnected(long j);

    private native void SendAudioData(long j, byte[] bArr, long j2);

    private native void SendVideoData(long j, byte[] bArr, int i, long j2, long j3, boolean z);

    private native void SetAudioParam(long j, int i, int i2, int i3, int i4, int i5);

    private native void SetCallback(long j, Object obj);

    private native void SetTransmissionParam(long j, int i, int i2);

    private native void SetVideoHeader(long j, byte[] bArr);

    private native void SetVideoParam(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7);

    private native void Start(long j, String str, String str2, int i, Object obj);

    private native void Stop(long j);

    private native void Uninit(long j);

    static {
        System.loadLibrary("hyrtmpupload");
    }

    public RtmpUpload(UploadInput uploadInput) {
        super(uploadInput);
        this.mNativeObjectPtr = 0L;
        this.mRtmpUploadInput = (RtmpUploadInput) uploadInput;
    }

    @Override // com.huya.force.export.upload.BaseUpload
    public void init() {
        this.mNativeObjectPtr = Init();
        ForceLog.info(TAG, "init");
        SetCallback(this.mNativeObjectPtr, this);
    }

    @Override // com.huya.force.export.upload.BaseUpload
    public void setListener(BaseUpload.Listener listener) {
        this.mListener = listener;
    }

    @Override // com.huya.force.export.upload.BaseUpload
    public void start() {
        ForceLog.info(TAG, "start");
        Map<RtmpUploadInput.TransmissionConfigKey, Integer> transmissionConfigKeyMap = this.mRtmpUploadInput.getTransmissionConfigKeyMap();
        if (transmissionConfigKeyMap != null) {
            for (Map.Entry<RtmpUploadInput.TransmissionConfigKey, Integer> entry : transmissionConfigKeyMap.entrySet()) {
                SetTransmissionParam(this.mNativeObjectPtr, entry.getKey().ordinal(), entry.getValue().intValue());
            }
        }
        setVideoParam(this.mRtmpUploadInput.getVideoParam());
        setAudioParam(this.mRtmpUploadInput.getAudioParam());
        Start(this.mNativeObjectPtr, this.mRtmpUploadInput.getUrl(), this.mRtmpUploadInput.getPath(), this.mRtmpUploadInput.getFlowControlPolicy().ordinal(), this.mRtmpUploadInput.getOnFlowControlListener());
    }

    @Override // com.huya.force.export.upload.BaseUpload
    public void setVideoHeader(byte[] bArr) {
        SetVideoHeader(this.mNativeObjectPtr, bArr);
    }

    @Override // com.huya.force.export.upload.BaseUpload
    public void setVideoParam(UploadInput.VideoParam videoParam) {
        SetVideoParam(this.mNativeObjectPtr, videoParam.getWidth(), videoParam.getHeight(), videoParam.getFrameRate(), this.mRtmpUploadInput.getMinVideoBitrate(), videoParam.getVideoBitrate(), videoParam.getVideoBitrate(), videoParam.getVideoEncodeType().ordinal());
    }

    @Override // com.huya.force.export.upload.BaseUpload
    public void setAudioParam(UploadInput.AudioParam audioParam) {
        SetAudioParam(this.mNativeObjectPtr, audioParam.getAacProfile().ordinal(), audioParam.getChannels(), audioParam.getSampleRate(), audioParam.getBitsPerSample(), audioParam.getAudioBitrate());
    }

    @Override // com.huya.force.export.upload.BaseUpload
    public void sendVideoData(VideoData videoData) {
        if (!isConnected()) {
            ForceLog.error(TAG, "sendVideoData not connected");
        }
        SendVideoData(this.mNativeObjectPtr, videoData.data, videoData.len, videoData.pts, videoData.dts, videoData.isKeyFrame);
    }

    @Override // com.huya.force.export.upload.BaseUpload
    public void sendAudioData(AudioData audioData) {
        if (!isConnected()) {
            ForceLog.error(TAG, "sendAudioData not connected");
        }
        SendAudioData(this.mNativeObjectPtr, audioData.data, audioData.pts);
    }

    @Override // com.huya.force.export.upload.BaseUpload
    public void stop() {
        ForceLog.info(TAG, "stop");
        Stop(this.mNativeObjectPtr);
    }

    @Override // com.huya.force.export.upload.BaseUpload
    public void uninit() {
        ForceLog.info(TAG, "uninit");
        Uninit(this.mNativeObjectPtr);
        this.mNativeObjectPtr = 0L;
    }

    @Override // com.huya.force.export.upload.BaseUpload.Listener
    public void onUploadResult(int i) {
        BaseUpload.Listener listener = this.mListener;
        if (listener != null) {
            listener.onUploadResult(i);
        }
    }

    @Override // com.huya.force.export.upload.BaseUpload
    public boolean isConnected() {
        return IsConnected(this.mNativeObjectPtr);
    }

    @Override // com.huya.force.export.upload.IUploadStatics
    public int getTotalSendBytes() {
        return GetTotalSendBytes(this.mNativeObjectPtr);
    }

    @Override // com.huya.force.export.upload.IUploadStatics
    public long getTotalSendAudioBytes() {
        return GetTotalSendAudioBytes(this.mNativeObjectPtr);
    }

    @Override // com.huya.force.export.upload.IUploadStatics
    public long getTotalSendVideoBytes() {
        return GetTotalSendVideoBytes(this.mNativeObjectPtr);
    }

    @Override // com.huya.force.export.upload.IUploadStatics
    public int getTotalSendAudioFrames() {
        return GetTotalSendAudioFrames(this.mNativeObjectPtr);
    }

    @Override // com.huya.force.export.upload.IUploadStatics
    public int getTotalSendVideoFrames() {
        return GetTotalSendVideoFrames(this.mNativeObjectPtr);
    }

    @Override // com.huya.force.export.upload.IUploadStatics
    public int getTotalSendTime() {
        return GetTotalSendTime(this.mNativeObjectPtr);
    }

    @Override // com.huya.force.export.upload.IUploadStatics
    public int getTotalSendBlockCount() {
        return GetTotalSendBlockCount(this.mNativeObjectPtr);
    }

    @Override // com.huya.force.export.upload.IUploadStatics
    public int getNetState() {
        return GetNetState(this.mNativeObjectPtr);
    }
}
