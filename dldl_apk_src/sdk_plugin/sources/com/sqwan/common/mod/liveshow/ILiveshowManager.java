package com.sqwan.common.mod.liveshow;

import android.app.Activity;
import android.content.Context;
import com.sqwan.common.mod.IModBase;
import com.sqwan.msdk.api.SQResultListener;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ILiveshowManager extends IModBase {
    Activity getLiveshowActivity();

    String getRoomId();

    String getUserId();

    boolean init();

    void initContext(Context context);

    void initLiveshowActivity(Context context);

    void initSkin(Context context);

    boolean isLiveShow();

    void joinLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener);

    void leaveLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener);

    void onActive(JSONObject jSONObject);

    void onSubmitRole();

    void pauseChannel();

    void performLiveshowFeature(Map<String, String> map, SQResultListener sQResultListener);

    void resumeChannel();

    void setLiveshowDestroyCallback(SQResultListener sQResultListener);

    void setLiveshowVoiceChangeCallback(SQResultListener sQResultListener);

    void uninit();
}
