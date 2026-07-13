package com.volcengine.cloudphone.apiservice;

import com.volcengine.androidcloud.common.api.IVideoDescription;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface StreamProfileManager {
    IVideoDescription getCurrentVideoStreamProfile();

    void setStreamProfileChangeListener(StreamProfileChangeCallBack streamProfileChangeCallBack);

    void switchVideoStreamProfileId(int i);
}
