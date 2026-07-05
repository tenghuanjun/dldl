package com.sqwan.msdk.api.tool;

import com.sqwan.msdk.api.SQResultListener;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface ILiveshow {
    boolean isSupportLiveVideo();

    void joinLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener);

    void leaveLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener);

    void performLiveshowFeature(Map<String, String> map, SQResultListener sQResultListener);

    void setLiveshowDestroyCallback(SQResultListener sQResultListener);

    void setLiveshowVoiceChangeCallback(SQResultListener sQResultListener);
}
