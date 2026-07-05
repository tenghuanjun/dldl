package com.sqwan.msdk.api.tool;

import com.sqwan.msdk.api.SQResultListener;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface ILiveRadio {
    boolean isSupportLiveRadio();

    void joinLiveRadioRoom(Map<String, String> map, SQResultListener sQResultListener);

    void leaveLiveRadioRoom(Map<String, String> map, SQResultListener sQResultListener);

    void performLiveRadioFeature(Map<String, String> map, SQResultListener sQResultListener);

    void setLiveRadioDestroyCallback(SQResultListener sQResultListener);

    void setLiveRadioVoiceChangeCallback(SQResultListener sQResultListener);
}
