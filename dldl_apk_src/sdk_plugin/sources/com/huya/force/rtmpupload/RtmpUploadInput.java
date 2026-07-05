package com.huya.force.rtmpupload;

import com.huya.force.export.upload.BaseUpload;
import com.huya.force.export.upload.UploadInput;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RtmpUploadInput extends UploadInput {
    FlowControlPolicy mFlowControlPolicy;
    int mMinVideoBitrate;
    BaseUpload.OnFlowControlListener mOnFlowControlListener;
    String mPath;
    Map<TransmissionConfigKey, Integer> mTransmissionConfigKeyMap;
    String mUrl;

    public enum FlowControlPolicy {
        kNoPolicy,
        kFastReactingPolicy,
        kTimeWindowsPolicy
    }

    public enum TransmissionConfigKey {
        kLowLayerRecvTimeout,
        kLowLayerSendTimeout,
        kUpperLayerRecvTimeout,
        kUpperLayerSendTimeout
    }

    public RtmpUploadInput(String str, String str2, FlowControlPolicy flowControlPolicy, BaseUpload.OnFlowControlListener onFlowControlListener, int i, Map<TransmissionConfigKey, Integer> map, UploadInput.VideoParam videoParam, UploadInput.AudioParam audioParam) {
        super(videoParam, audioParam);
        this.mUrl = str;
        this.mPath = str2;
        this.mFlowControlPolicy = flowControlPolicy;
        this.mOnFlowControlListener = onFlowControlListener;
        this.mTransmissionConfigKeyMap = map;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public String getPath() {
        return this.mPath;
    }

    public void setPath(String str) {
        this.mPath = str;
    }

    public FlowControlPolicy getFlowControlPolicy() {
        return this.mFlowControlPolicy;
    }

    public BaseUpload.OnFlowControlListener getOnFlowControlListener() {
        return this.mOnFlowControlListener;
    }

    public int getMinVideoBitrate() {
        return this.mMinVideoBitrate;
    }

    public Map<TransmissionConfigKey, Integer> getTransmissionConfigKeyMap() {
        return this.mTransmissionConfigKeyMap;
    }
}
