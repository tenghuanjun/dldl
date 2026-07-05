package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class UploadProgress {

    @SerializedName("Percent")
    private float percent;

    @SerializedName("RequestId")
    private long requestId;

    public void setRequestId(long j) {
        this.requestId = j;
    }

    public long getRequestId() {
        return this.requestId;
    }

    public void setPercent(float f) {
        this.percent = f;
    }

    public float getPercent() {
        return this.percent;
    }
}
